 package com.n9.service.impl;

import java.util.List;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.n9.dao.BlogLogDao;
import com.n9.entity.BlogLog;
import com.n9.service.LogService;
import com.n9.util.PageQueryUtil;
import com.n9.util.PageResult;

@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	private BlogLogDao blogLogDao;

	/*
	 * @Override public int getTotalBlogs() {
	 * System.out.println(blogLogDao.getRowCount(null)); return
	 * blogLogDao.getRowCount(null); }
	 */

	/**
	 * 对日志进行分页查询并可以模糊搜索
	 */
	@Override
	public PageResult getBlogsPage(PageQueryUtil pageUtil) {
		List<BlogLog> blogList = blogLogDao.findPageObjects(pageUtil);
        int total = blogLogDao.getRowCount(pageUtil);
        System.out.println(total);
        PageResult pageResult = new PageResult(blogList, total, pageUtil.getLimit(), pageUtil.getPage());
        //System.out.println("pageResult="+pageResult);
        return pageResult;
	}
	
	/**
	 * 删除日志
	 */
	 @Override
	    public Boolean deleteBatch(Integer[] ids) {
	        return blogLogDao.deleteObjects(ids) > 0;
	    }

	 /**
	  * 写日志
	  */
	@Override
	public Future<Integer> saveObject(BlogLog entity) {
		String tName=Thread.currentThread().getName();
		System.out.println("log.save.thread.name="+tName);
		int rows=blogLogDao.insertObject(entity);
		//try{Thread.sleep(10000);}catch(Exception e) {}
		return new AsyncResult<Integer>(rows);
	}//假如异步操作中调用的方法有返回值，可以使用AsyncResult对象进行封装。
	
	
	
}
