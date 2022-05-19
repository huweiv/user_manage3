package com.huweiv.dao;

import com.huweiv.domain.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author HUWEIV
 * @version 1.0.0
 * @ClassName RoleMapper
 * @Description TODO
 * @CreateTime 2022/4/10 10:25
 */
@Mapper
public interface RoleDao {

    @Select("select * from sys_role")
    List<Role> selectAll();

    @Insert("insert into sys_role values(#{id}, #{roleName}, #{roleDesc})")
    int save(Role role);

    @Delete("delete from sys_role where id=#{roleId}")
    int deleteById(long roleId);

    @Delete("delete from sys_user_role where roleId=#{roleId}")
    void deleteUserIdAndRoleIdByRoleId(long roleId);

    int update(Role role);

    @Select("select * from sys_role where roleName=#{roleName}")
    Role findRoleByRoleName(String roleName);

    void deleteUserIdAndRoleIdByRoleIds(Long[] roleId);

    int deleteByIds(Long[] roleId);

    List<Role> selectByCondition(Role role);
}
