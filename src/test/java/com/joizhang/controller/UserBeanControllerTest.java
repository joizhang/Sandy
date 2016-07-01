package com.joizhang.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.joizhang.model.vo.UserBean;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath*:/spring*.xml" })
public class UserBeanControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testTest() throws Exception {
		mockMvc.perform(get("/test"))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	public void testSaveUser() throws Exception {
		UserBean userBean = new UserBean();
		userBean.setUsername("123");
		userBean.setPassword("123");
		System.out.println(userBean.toString());
		String user = "{\"username\":\"123\", \"password\":\"123\"}";
		System.out.println(user);
		mockMvc.perform(post("/user/saveUser").contentType(MediaType.APPLICATION_JSON).content(user))
				.andExpect(status().isOk())
				.andDo(print());
	}

}
