package com.pan.user.model.vo;

import com.pan.user.model.domain.UserInfo;
import com.pan.user.model.domain.UserLoginLog;

import lombok.Data;

@Data
public class UserInfoVo {

	/**
	 * 用户基础信息
	 */
	private UserInfo userInfo;
	
	/**
	 * 用户登录日志信息
	 */
	private UserLoginLog userLoginLog;
	
	/**
	 * 修改用户信息时用于表示是否修改了密码
	 */
	private int updateUserInfoPwdIsChange=0;
	
	
	/**
	 * 修改角色的类型 1 添加角色 2 删除角色
	 */
	private int updateRoleIdsType;
	
	
	
}
