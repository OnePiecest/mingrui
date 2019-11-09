package com.n9.dao;

import com.n9.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogUserMapper {


    List<AdminUser> findBlogUserList(Map map);

    int getTotalBlogUsers(Map map);


    AdminUser selectByUserName(@Param("loginUserName") String userName);


    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    int deleteBatch(@Param("ids") Integer... ids);

}
