package com.joizhang.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joizhang.dao.BaseDaoI;
import com.joizhang.model.po.RmsOperLog;
import com.joizhang.model.vo.OperLog;
import com.joizhang.model.vo.Pager;
import com.joizhang.service.IOperLogService;

@Service("OperLogService")
public class OperLogServiceImpl implements IOperLogService {
	@Autowired
	private BaseDaoI<RmsOperLog> rmsOperLoggDao;				//只对日志进行查询操作，所以只需要vo,不需要po
	
	/**
	 * 以分页的方式获得日志
	 * @param pager 初始化分页的一些参数
	 * @param operLog 查询条件
	 * @author joizhang
	 * */
	public Pager<OperLog> getLogPager(Pager<OperLog> pager, OperLog operLog) {
		Pager<OperLog> returnPager = new Pager<OperLog>();
		//算出总页数
		returnPager.setTotalPage(rmsOperLoggDao.countPage("select count(*) from RmsOperLog", pager.getPageSize()));
		//获得记录集
		returnPager.setDataList(changeModel(rmsOperLoggDao.find("from RmsOperLog t where t.operationMan=?0", new Object[]{operLog.getOperationMan()}, pager.getCurrentPage(), pager.getPageSize())));
		//当前页
		returnPager.setCurrentPage(pager.getCurrentPage());
		//每页记录数
		returnPager.setPageSize(pager.getPageSize());
		
		return returnPager;
	}
	
	private List<OperLog> changeModel(List<RmsOperLog> RmsOperLogs) {		//将RmsUser转换为User
		List<OperLog> operLogs = new ArrayList<OperLog>();
		if (RmsOperLogs != null && RmsOperLogs.size() > 0) {
			for (RmsOperLog rmsOperLog : RmsOperLogs) {
				OperLog operLog = new OperLog();
				BeanUtils.copyProperties(rmsOperLog, operLog);

				operLogs.add(operLog);
			}
		}
		return operLogs;
	}
	/**
	 * 获取某一条日志
	 * @param operLog 查询条件
	 * @author joizhang
	 * */
	public OperLog getLog(OperLog operLog){
		RmsOperLog rmsOperLog = new RmsOperLog();
		rmsOperLog = rmsOperLoggDao.get("from RmsOperLog t where t.operationId=?0", new Object[]{ operLog.getOperationId() });
		
		if (rmsOperLog != null) {
			BeanUtils.copyProperties(rmsOperLog, operLog);
		}
		
		return operLog;
	}
}
