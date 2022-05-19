package com.huweiv;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.huweiv.dao.RoleDao;
import com.huweiv.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserManageApplicationTests {

	@Autowired
	private RoleDao roleMapper;
	@Autowired
	private DefaultKaptcha defaultKaptcha;

	@Test
	void contextLoads() {
		try {
			int i = 1 / 0;
		} catch (Exception e) {
			throw new BusinessException(12, "sdad", e);
		}
//		PageHelper.startPage(0, 5);
//		List<Role> roleList = roleMapper.selectByCondition(new Role());
//		PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
//		int totalCount = (int) pageInfo.getTotal();
//		System.out.println(roleList);
//		System.out.println(totalCount);
//		@Value("")
//		String str;
//		System.out.println(str);


	}

}
