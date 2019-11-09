package com.n9.service;

import com.n9.util.PageQueryUtil;
import com.n9.util.PageResult;


public interface UserService {



    /**
     * 查询用户分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getUsersPage(PageQueryUtil pageUtil);

    int getTotalUsers();


    Boolean saveUser(String userName,String userPassword,String nickName);

    Boolean deleteBatch(Integer[] ids);


}
