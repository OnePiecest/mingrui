package com.n9.service.impl;

import com.n9.dao.BlogUserMapper;
import com.n9.entity.AdminUser;
import com.n9.service.UserService;
import com.n9.util.MD5Util;
import com.n9.util.PageQueryUtil;
import com.n9.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BlogUserMapper userMapper;

    @Override
    public PageResult getUsersPage(PageQueryUtil pageUtil) {
        List<AdminUser> users = userMapper.findBlogUserList(pageUtil);
        int total = userMapper.getTotalBlogUsers(pageUtil);
        PageResult pageResult = new PageResult(users, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalUsers() {
        return userMapper.getTotalBlogUsers(null);
    }


    @Override
    public Boolean saveUser(String userName, String userPassword, String nickName) {
        AdminUser temp = userMapper.selectByUserName(userName);
        if (temp == null) {
            AdminUser user = new AdminUser();
            user.setLoginUserName(userName);

            //密码加密
            String password = MD5Util.MD5Encode(userPassword, "UTF-8");



            user.setLoginPassword(password);
            user.setNickName(nickName);
            return userMapper.insertSelective(user) > 0;
        }
        return false;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return userMapper.deleteBatch(ids) > 0;
    }

}
