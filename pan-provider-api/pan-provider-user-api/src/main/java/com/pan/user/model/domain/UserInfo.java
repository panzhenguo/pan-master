package com.pan.user.model.domain;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;

@Data
@Table(name = "aw_user_info")
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 用户密码
	 */
	@Column(name = "user_pwd")
	private String userPwd;

	/**
	 * 密码盐值
	 */
	@Column(name = "pwd_salt")
	private String pwdSalt;

	/**
	 * 用户昵称
	 */
	@Column(name = "user_nike_name")
	private String userNikeName;

	/**
	 * 用户性别 0女 1男
	 */
	@Column(name = "user_sex")
	private Integer userSex;

	/**
	 * 用户手机号
	 */
	@Column(name = "user_phone")
	private String userPhone;

	/**
	 * 用户创建时间
	 */
	@Column(name = "create_date")
	private Date createDate;

	/**
	 * 用户创建人(0 数据库手动添加)
	 */
	@Column(name = "create_user_id")
	private Integer createUserId;

	/**
	 * 用户最后登录时间
	 */
	@Column(name = "user_last_login_date")
	private Date userLastLoginDate;

	/**
	 * 用户最后登录地址
	 */
	@Column(name = "user_last_login_ip")
	private String userLastLoginIp;

	/**
	 * 用户状态 0 禁用 1正常
	 */
	@Column(name = "user_status")
	private Integer userStatus;

	/**
	 * 角色ID 集合
	 */
	@Column(name = "user_role_ids")
	private String userRoleIds;

	/**
	 * 用户头像
	 */
	@Column(name = "head_img")
	private String headImg;

}