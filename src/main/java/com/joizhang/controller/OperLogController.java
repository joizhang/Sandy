package com.joizhang.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joizhang.model.vo.OperLog;
import com.joizhang.model.vo.Pager;
import com.joizhang.service.IOperLogService;
import com.joizhang.util.ExceptionUtil;

@Controller
public class OperLogController {
	private static final Logger logger = Logger.getLogger(OperLogController.class);
	
	@Autowired
	private IOperLogService operLogService;
	
	/**
	 * 用http://esimakin.github.io/twbs-pagination/实现的分页页面
	 * @author joizhang
	 * @param
	 * */
	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public String loglist(HttpServletRequest request, Model model) {
		System.out.println("==========>> log");
		Pager<OperLog> pager = new Pager<OperLog>();
		OperLog operLog = new OperLog();
		operLog.setOperationMan("admin");
		
		String page = WebUtils.getCleanParam(request, "page");
		
		System.out.print(page);
		
		if(page != null){
			pager.setCurrentPage(Integer.parseInt(page));
		} else {
			pager.setCurrentPage(1);
		}
		
		pager.setPageSize(15);										//每页显示15条记录
		
		try{
			pager = operLogService.getLogPager(pager, operLog);
		}catch(Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			return null;
		}
		
		model.addAttribute("pager",pager);
		return "view/sys/log";
	}
	
	@RequestMapping(value = "/getLogOperationContent", method = RequestMethod.POST)
	@ResponseBody
	public OperLog getLogOperationContent(HttpServletRequest request) {
		OperLog operLog = new OperLog();
		String operationId = WebUtils.getCleanParam(request, "operationId");
		
		if (operationId != null) {
			operLog.setOperationId(operationId);
		} else {
			return null;
		}
		
		try {
			operLog = operLogService.getLog(operLog);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			return null;
		}
		return operLog;
	}
}
