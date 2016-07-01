package com.joizhang.controller;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joizhang.model.vo.UserBean;

@Controller
public class UserBeanController {
	
	private static final Logger logger = Logger.getLogger(UserBeanController.class);
	
	@RequestMapping(value = "/test", method = RequestMethod.GET ) 
	public String test(){
		return "view/sys/test";
	}
	
	@RequestMapping(value = "/user/saveUser", method = RequestMethod.POST) 
    @ResponseBody  
    public String saveUser(@RequestBody UserBean users) { 
        //userService.batchSave(users); 
		logger.info(ReflectionToStringBuilder.toString(users));
		return "ok";
    } 	
}
