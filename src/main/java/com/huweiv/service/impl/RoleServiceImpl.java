package com.huweiv.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huweiv.dao.RoleDao;
import com.huweiv.domain.PageBean;
import com.huweiv.domain.Result;
import com.huweiv.domain.Role;
import com.huweiv.service.RoleService;
import com.huweiv.util.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author HUWEIV
 * @version 1.0.0
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @CreateTime 2022/4/10 10:28
 */
@Service("roleService")
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Result add(Role role) {
        Role roleInfo = roleDao.findRoleByRoleName(role.getRoleName());
        if (roleInfo != null)
            return new Result(Code.SAVE_ERR, "角色名已被注册");
        boolean flag = roleDao.save(role) > 0;
        Integer code = flag ? Code.SAVE_OK : Code.SAVE_ERR;
        String msg = flag ? "添加成功" : "添加失败";
        return new Result(code, msg);
    }

    @Override
    public Result edit(Role role, int isUpdateRoleName) {
        if (isUpdateRoleName == 0)
            role.setRoleName(null);
        else {
            Role roleInfo = roleDao.findRoleByRoleName(role.getRoleName());
            if (roleInfo != null)
                return new Result(Code.UPDATE_ERR, "角色名已被注册");
        }
        boolean flag = roleDao.update(role) > 0;
        Integer code = flag ? Code.UPDATE_OK : Code.UPDATE_ERR;
        String msg = flag ? "编辑成功" : "编辑失败";
        return new Result(code, msg);
    }

    @Override
    public Result delete(Long roleId) {
        roleDao.deleteUserIdAndRoleIdByRoleId(roleId);
        boolean flag = roleDao.deleteById(roleId) > 0;
        Integer code;
        String msg;
        code = flag ? Code.DELETE_OK : Code.DELETE_ERR;
        msg = flag ? "删除成功" : "删除失败";
        return new Result(code, msg);
    }

    @Override
    public Result batchDelete(Long[] roleIds) {
        roleDao.deleteUserIdAndRoleIdByRoleIds(roleIds);
        boolean flag = roleDao.deleteByIds(roleIds) > 0;
        Integer code;
        String msg;
        code = flag ? Code.DELETE_OK : Code.DELETE_ERR;
        msg = flag ? "删除成功" : "删除失败";
        return new Result(code, msg);
    }

    @Override
    public PageBean<Role> list(Role role, int currentPage, int pageSize) {
        if (role.getRoleName() != null && role.getRoleName().length() > 0)
            role.setRoleName("%" + role.getRoleName() + "%");
        if (role.getRoleDesc() != null && role.getRoleDesc().length() > 0)
            role.setRoleDesc("%" + role.getRoleDesc() + "%");
        PageHelper.startPage(currentPage, pageSize);
        List<Role> roleList = roleDao.selectByCondition(role);
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
        int totalCount = (int) pageInfo.getTotal();
        PageBean<Role> pageBean = new PageBean<>();
        pageBean.setRows(roleList);
        pageBean.setTotalCount(totalCount);
        return pageBean;
    }

    @Override
    public List<Role> selectAll() {
        return roleDao.selectAll();
    }
}
