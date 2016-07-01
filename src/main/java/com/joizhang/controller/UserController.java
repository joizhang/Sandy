package com.joizhang.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.joizhang.util.ExceptionUtil;
import com.joizhang.service.IUserService;
import com.joizhang.controller.UserController;
import com.joizhang.model.vo.User;

@Controller
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/adduser")
	public String input() {
		System.out.println("============> input");
		return "view/sys/input";
	}
	
	/**
	 * 用户添加（暂时未存session）
	 * @author joizhang
	 * @param user user vo 对象
	 * @param model
	 * */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> add(HttpServletRequest request) {		//添加用户
		System.out.println("============> add");
		User user = new User();
		Map<String,Object> map = new HashMap<String,Object>();
		
		String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		String password = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM);
		user.setUsername(username);
		user.setPassword(password);
		
		if(userService.getUserByName(user) == null){
			try {			
				userService.save(user);
				map.put("msg", "成功");
				return map;
			} catch (Exception e) {
				logger.error(ExceptionUtil.getExceptionMessage(e));
				return null;
			}
		}else{
			logger.error("Can not add "+user.getUsername());
			return null;
		}
	}
	
	/**
	 * 根据session来获取该用户的下级用户
	 * @author joizhang
	 * @param
	 * */
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)		//获取所有用户
	public String userlist(User user, Model model) {
		System.out.println("============> add");
		List<User> userlist = userService.getUserList(user); 
		model.addAttribute("userlist", userlist);
		return "view/sys/userlist";
	}
	
	/**
	 * 删除用户
	 * @author joizhang
	 * @param request
	 * */
	@RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteuser(HttpServletRequest request) {
		String userid = WebUtils.getCleanParam(request, "userid");
		Map<String,Object> map = new HashMap<String,Object>();
		
		String message = "";
		try {
			message = userService.delete(userid);
			
			if(message.equals("success")) {
				map.put("msg", "成功");
				System.out.println(userid);
				return map;
			} else {
				return null;
			}
		} catch(Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			return null;
		}
	}
	
	@RequestMapping(value = "/getUserDetail", method = RequestMethod.POST)
	@ResponseBody
	public User getUserDetail(HttpServletRequest request){
		String userid = WebUtils.getCleanParam(request, "userid");
		User user = new User();
		user.setUserid(userid);
		
		try{
			user = userService.getUserByUserid(user);
			if (user != null) {
				return user;
			} else {
				return null;
			}
		} catch(Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			return null;
		}
	}
	
	/**
	 * 定位到fileUpload.jsp
	 * */
	@RequestMapping(value = "/fileupload", method = RequestMethod.GET)
	public String fileupload(){
		return "view/fileUpload/fileUpload";
	}
	
	/**
	 * 上传文件
	 * */
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> upload(@RequestParam("myFiles") MultipartFile files, HttpServletRequest request){
		System.out.println("=========>> upload");
		Map<String,Object> map = new HashMap<String,Object>();
		
		if (!files.isEmpty()){
			String realPath = request.getSession().getServletContext().getRealPath("/");
			String uploadFileFileName = files.getOriginalFilename();									//获取源文件名
			String fileType = uploadFileFileName.substring(uploadFileFileName.lastIndexOf("."));	//获取源文件类型
			
			System.out.println(
					"uploadFileFileName:"+uploadFileFileName+"\n"+
					"fileType:"+fileType+"\n"+
					"fileSize:"+files.getSize()
			);
			
			String fileFolder = realPath + "static\\fileUpload\\";
			
			try {	
				File targetFile = new File(fileFolder, uploadFileFileName);
				files.transferTo(targetFile);
				map.put("msg", "成功");
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		System.out.println(map);
		return map;
	}
	
	/**
	 * 定位到gallery.jsp
	 * */
	@RequestMapping(value = "/gallery", method = RequestMethod.GET)
	public String gallery(){
		return "view/fileUpload/gallery";
	}
	
	/**
	 * 导出PDF，不能用jQuery ajax因为不支持流式传输
	 * */
	@RequestMapping(value = "/exportPDF", method = RequestMethod.GET)
	public String exportPDF(HttpServletRequest request, HttpServletResponse response){
		System.out.println("=========>> exportPDF");
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String photoPath = realPath + "static\\fileUpload\\";
		String pdfDownload = realPath + "static\\export\\";
		userService.exportAllUser(photoPath, pdfDownload);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=test.pdf");
		
		try {
			File file = new File(pdfDownload+"test.pdf");
			System.out.println(file.exists()+">>>>>>>>>>>");
			InputStream inputStream = new FileInputStream(new File(pdfDownload+"test.pdf"));
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			// 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("2="+e);
		} catch (IOException e) {
			e.printStackTrace();

			System.out.println("3="+e);
		}
		return null;
	}
	
}
