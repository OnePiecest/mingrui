package com.n9.entity;

import java.util.Date;

import lombok.Data;

@Data
public class AdminUser {
    private Integer adminUserId;

    private String loginUserName;

    private String loginPassword;

    private String nickName;

    private Date createdTime;//创建时间

    private Date modifiedTime;//修改时间
    
    private Byte locked;

    
}