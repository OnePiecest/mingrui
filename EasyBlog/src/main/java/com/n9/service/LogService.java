package com.n9.service;

import java.util.concurrent.Future;

import com.n9.entity.BlogLog;
import com.n9.util.PageQueryUtil;
import com.n9.util.PageResult;

public interface LogService {

	//int getTotalBlogs();
	
	PageResult getBlogsPage(PageQueryUtil pageUtil);
	
	Boolean deleteBatch(Integer[] ids);
	
	/**
	 * 写用户行为日志到数据库
	 * @param entity
	 * @return
	 */
	Future<Integer> saveObject(BlogLog entity);
}
