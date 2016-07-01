package com.joizhang.service;

import com.joizhang.model.vo.OperLog;
import com.joizhang.model.vo.Pager;

public interface IOperLogService {
	/* 以分页的方式获得日志 */
	public Pager<OperLog> getLogPager(Pager<OperLog> pager, OperLog operLog);
	
	/* 获取某一条日志 */
	public OperLog getLog(OperLog operLog);
}
