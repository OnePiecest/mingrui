package com.n9.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class BlogLog implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer logId;
	// 用户名
	private String username;
	// 用户操作
	private String operation;
	// 请求方法
	private String method;
	// 请求参数
	private String params;
	// 执行时长(毫秒)
	private Long time;
	// IP地址
	private String ip;
	// 创建时间
	private Date createdTime;
}
