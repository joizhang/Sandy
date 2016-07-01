package com.joizhang.service;

import java.util.List;

import com.joizhang.model.vo.User;

public interface IUserService {

	public String login(User user);

	public void save(User user);
	
	public void update(User user);

	public String delete(String ids);
	
	public User getUserByName(User user);
	
	public User getUserByUserid(User user);
	
	public List<User> getUserList(User user);

	public void exportAllUser(String photoPath, String pdfDownload);

	/**
	 * 通过用户名找到该用户
	 * @param username 用户名
	 * */
	public User findByUsername(String username);
}
