package com.joizhang.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joizhang.model.vo.User;

@Controller
@RequestMapping("/api")
public class ApiController {

	@RequestMapping(value="/user")
	@ResponseBody
	public User getUser(HttpServletRequest request) {
		String name = request.getParameter("name").toString();
		User user = new User();
		if (name.equals("123")) {
			user.setUserid("121212121");
			user.setUsername("123");
			user.setPassword("123");
			return user;
		} else {
			return null;
		}
	}
}
