package com.n9.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.n9.entity.BlogLog;
import com.n9.util.PageQueryUtil;

@Mapper
public interface BlogLogDao {
	
	/**
	 * 负责基于条件查询总记录数
	 * @param pageUtil
	 * @return
	 */
	int getRowCount(PageQueryUtil pageUtil);

	/**
	 * 负责基于条件查询当前页数据
	 * @param pageUtil
	 * @return
	 */
	List<BlogLog> findPageObjects(PageQueryUtil pageUtil);
	
	/**
	 * 基于id执行删除操作
	 * 
	 * @param ids
	 * @return
	 */
	int deleteObjects(Integer... ids);
	
	/**
	 * 	添加用户行为日志
	 * 
	 * @param entity
	 * @return
	 */
	int insertObject(BlogLog entity);
}
