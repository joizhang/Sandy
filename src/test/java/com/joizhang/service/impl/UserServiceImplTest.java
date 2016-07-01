package com.joizhang.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.joizhang.model.vo.User;
import com.joizhang.service.IUserService;

@ContextConfiguration(locations = {"classpath*:/spring-hibernate.xml", "classpath*:/spring.xml",})
public class UserServiceImplTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private IUserService userService;
	
	@Test
	public void testSave() {
		User user = new User();
		user.setEnabled(1);
		user.setUsername("123");
		user.setPassword("123");
		userService.save(user);
	}

}
