package com.joizhang.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joizhang.dao.BaseDaoI;
import com.joizhang.model.po.RmsOperLog;
import com.joizhang.model.po.RmsUser;
import com.joizhang.model.vo.User;
import com.joizhang.service.IUserService;
import com.joizhang.util.Encrypt;
import com.joizhang.util.ItextPdfTableUtil;
import com.joizhang.util.PasswordHelper;

@Transactional
@Service("UserService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private BaseDaoI<RmsUser> userDao;
	@Autowired
	private BaseDaoI<RmsOperLog> operLogDao;
	/**
	 * find user by user's name
	 * @author Administrator
	 * @param user
	*/ 
	public String login(User user) {		//login method
		RmsUser t = userDao.get("from RmsUser t where t.username = ?0 and t.password = ?1 ", new Object[] { user.getUsername(), Encrypt.e(user.getPassword()) });
		if (t != null) {
			return "success";
		}
		return "failed";
	}

	/**
	 * save method
	 * @author Administrator
	 * @param user
	 */
	public void save(User user) {	
		RmsUser u = new RmsUser();
		PasswordHelper passwordHelper = new PasswordHelper();
		passwordHelper.encryptPassword(user);
		BeanUtils.copyProperties(user, u);		
		u.setUserid(UUID.randomUUID().toString());
		
		if (user.getCreatedtime() == null) {
			//generate date
			u.setCreatedtime(new Timestamp(System.currentTimeMillis()));
		}
		
		userDao.save(u);
		
		//记录日志
		String sql="insert into Rms_user (userid,username,password,enabled,createdtime,role_id) values (\'"+u.getUserid()+"\',\'"+u.getUsername()+"\',\'"+u.getPassword()+"\',1,"+u.getCreatedtime()+",0)";
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		RmsOperLog operLog = new RmsOperLog();
		operLog.setOperationId("LOGID"+String.valueOf(year)+UUID.randomUUID().toString().substring(25));
		operLog.setOperationMan("admin");
		operLog.setOperationTable("Rms_user");
		operLog.setOperationContent(sql);
		operLog.setOperationDate(new Timestamp(System.currentTimeMillis()));
		operLogDao.save(operLog);
	}

	/**
	 * update method
	 * @author Administrator
	 * @param user
	*/ 
	public void update(User user) {	
		RmsUser u = userDao.get(RmsUser.class, user.getUserid());
		BeanUtils.copyProperties(user, u, new String[] { "cid"});
		if (user.getPassword() != null && !user.getPassword().trim().equals("")) {
			u.setPassword(Encrypt.e(user.getPassword()));
		}
		userDao.update(u);
	}
	
	/**
	 * delete method
	 * @author Administrator
	 * @param ids 用户的id
	*/ 
	public String delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				RmsUser u = userDao.get(RmsUser.class, id.trim());
				if (u != null) {
					userDao.delete(u);
					
					//记录日志
					String sql="delete from Rms_user where userid='"+id+"'";
					Calendar cal = Calendar.getInstance();
					int year = cal.get(Calendar.YEAR);
					RmsOperLog operLog = new RmsOperLog();
					operLog.setOperationId("LOGID"+String.valueOf(year)+UUID.randomUUID().toString().substring(25));
					operLog.setOperationMan("admin");
					operLog.setOperationTable("Rms_user");
					operLog.setOperationContent(sql);
					operLog.setOperationDate(new Timestamp(System.currentTimeMillis()));
					operLogDao.save(operLog);
				}
			}
			
			return "success";
		}
		return "failed";
	}
	
	public User getUserByName(User user) {
		RmsUser t = userDao.get("from RmsUser t where t.username = ?0", new Object[] { user.getUsername() });
		if (t != null) {
			BeanUtils.copyProperties(t, user);
			return user;
		}
		return null;
	}
	
	public User getUserByUserid(User user){
		RmsUser t = userDao.get("from RmsUser t where t.userid = ?0", new Object[] { user.getUserid() });
		if (t != null) {
			BeanUtils.copyProperties(t, user);
			return user;
		}
		return null;
	}
	
	/**
	 * find all user
	 * @author Administrator
	 * @param User user
	 */ 
	public List<User> getUserList(User user){
		String hql = "from RmsUser order by createdtime desc";
		List<RmsUser> RmsUsers = new ArrayList<RmsUser>();
		RmsUsers = userDao.find(hql);
	
		return changeModel(RmsUsers);
	}
	
	private List<User> changeModel(List<RmsUser> RmsUsers) {		//将RmsUser转换为User
		List<User> users = new ArrayList<User>();
		if (RmsUsers != null && RmsUsers.size() > 0) {
			for (RmsUser tu : RmsUsers) {
				User u = new User();
				BeanUtils.copyProperties(tu, u);

				users.add(u);
			}
		}
		return users;
	}
	
	@SuppressWarnings("unused")
	private Long total(User user) {
		String hql = "select count(*) from RmsUser t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(user, hql, values);
		return userDao.count(hql, values);
	}

	private String addWhere(User user, String hql, List<Object> values) {
		if (user.getUsername() != null && !user.getUsername().trim().equals("")) {
			hql += " and t.cname like ? ";
			values.add("%%" + user.getUsername().trim() + "%%");
		}
		return hql;
	}
	
	/**
	 * export all user into PDF
	 * @param photoPath photo location
	 * @param pdfDownload pdf download location
	 * */
	public void exportAllUser(String photoPath, String pdfDownload){
		List<RmsUser> list = new ArrayList<RmsUser>();
		list = userDao.find("from RmsUser");
		System.out.println(list.get(0).getUserid());
		
		ItextPdfTableUtil iptu = new ItextPdfTableUtil();
		iptu.exportAllUser(list, photoPath, pdfDownload);
	}
	
	public static void main(String[] args){
		System.out.println(new Timestamp(new Date().getTime()));
	}

	public User findByUsername(String username) {
        System.out.println("findByUsername");
        List<RmsUser> rmsUserList = userDao.find("from RmsUser where username=?0", new Object[]{username});
        User user = new User();
        if (rmsUserList.size() > 0) {
            BeanUtils.copyProperties(rmsUserList.get(0), user);
        }
        //logger.info(ReflectionToStringBuilder.toString(user));
        return user;
    }
}
