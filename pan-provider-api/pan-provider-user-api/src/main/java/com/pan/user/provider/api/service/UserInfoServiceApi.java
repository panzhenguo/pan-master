package com.pan.user.provider.api.service;

import javax.print.attribute.standard.Media;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pan.core.MediaType;
import com.pan.core.Result;
import com.pan.user.model.domain.UserInfo;
import com.pan.user.model.vo.UserInfoVo;

/**
 * 用户方法接口
 * 
 * @Title: UserInfoServiceApi.java
 * @Package com.bjttsf.user.provider.api.serivce
 * @ProjectName bc-provider-user-api
 * @Description:
 * @Author ttsf-pzg
 * @Date 2018年7月24日下午12:52:03
 * @Version V1.0
 */
@RequestMapping(value="/userInfo",produces=MediaType.JSON_UTF_8)
public interface UserInfoServiceApi {

	/**
	 * 根据用户ID 修改用户角色信息
	 * 
	 * @Title: updateUserRoleIdsByUserInfoId
	 * @Description:
	 * @CreateTime 2018年7月27日下午1:57:09
	 * @Author ttsf-pzg
	 * @param userInfoVo
	 * @return
	 */
	@RequestMapping("/updateUserRoleIdsByUserInfoId")
	public Result updateUserRoleIdsByUserInfoId(UserInfoVo vo);

	/**
	 * 检查用户是否存在
	 * 
	 * @Title: checkUserName
	 * @Description:
	 * @CreateTime 2018年7月27日下午1:57:30
	 * @Author ttsf-pzg
	 * @param userName
	 * @return
	 */
	@RequestMapping("/checkUserName/{userName}")
	public Result checkUserName(@PathVariable("userName") String userName);

	/**
	 * 根据传入类型修改一个用户的状态
	 * 
	 * @Title: changeUserInfoStatus
	 * @Description:
	 * @CreateTime 2018年7月24日下午3:07:53
	 * @Author ttsf-pzg
	 * @param userId
	 * @param changeType
	 *            0 禁用 1 启用
	 * @return
	 */
	@RequestMapping("/changeUserInfoStatus/{userId}/{changeType}")
	public Result changeUserInfoStatus(@PathVariable("userId") int userId, @PathVariable("changeType") int changeType);

	/**
	 * 根据ID 查询一个用户
	 * 
	 * @Title: selectUserInfoById
	 * @Description:
	 * @CreateTime 2018年7月24日下午2:41:31
	 * @Author ttsf-pzg
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectUserById/{id}")
	public Result selectUserInfoById(@PathVariable("id") int id);

	/**
	 * 添加一个用户
	 * 
	 * @Title: addUserInfo
	 * @Description:
	 * @CreateTime 2018年7月24日下午2:41:43
	 * @Author ttsf-pzg
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("/addUserInfo")
	@ResponseBody
	public Result addUserInfo(UserInfo userInfo);

	/**
	 * 根据ID 删除一个用户
	 * 
	 * @Title: deleteUserInfoByUserId
	 * @Description:
	 * @CreateTime 2018年7月24日下午2:41:52
	 * @Author ttsf-pzg
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteUserInfoByUserId/{id}")
	public Result deleteUserInfoByUserId(@PathVariable("id") int id);

	/**
	 * 根据ID 修改一个用户对象
	 * 
	 * @Title: updateUserInfoByUserId
	 * @Description:
	 * @CreateTime 2018年7月24日下午2:42:07
	 * @Author ttsf-pzg
	 * @param vo
	 * @return
	 */
	@RequestMapping("/updateUserInfoByUserId")
	public Result updateUserInfoByUserId(UserInfoVo vo);

	/**
	 * 用户登录方法
	 * 
	 * @Title: userLogin
	 * @Description:
	 * @CreateTime 2018年7月24日下午2:18:52
	 * @Author ttsf-pzg
	 * @param userInfo
	 *            登录用户信息
	 * @param ull
	 *            登录日志信息
	 * @return
	 */
	@RequestMapping("/userLoign")
	public Result userLogin(UserInfoVo userInfoVo);

	/**
	 * 修改用户自己的密码
	 * 
	 * @Title: userUpdatePwd
	 * @Description:
	 * @CreateTime 2018年7月24日下午2:19:26
	 * @Author ttsf-pzg
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("/userUpdatePwd")
	public Result userUpdatePwd(UserInfo userInfo);

	/**
	 * 根据角色ID 查询用户信息
	 * 
	 * @Title: selectUserInfoByRoleIds
	 * @Description:
	 * @CreateTime 2018年7月27日下午2:05:56
	 * @Author ttsf-lfs
	 * @param userRoleIds
	 * @return
	 */
	@RequestMapping("/selectUserInfoByRoleIds")
	public Result selectUserInfoByRoleIds(UserInfo userInfo);

	/**
	 * 分页查询UserInfo列表
	 * 
	 * @Title: selectUserInfoList
	 * @Description:
	 * @CreateTime 2018年8月3日下午1:54:03
	 * @Author ttsf-lfs
	 * @return
	 */
	@RequestMapping("/selectUserInfoListByPage/{num}/{size}")
	public Result selectUserInfoListByPage(@PathVariable("num") int num, @PathVariable("size") int size);

	/**
	 * 查询所有userInfo数据
	 * 
	 * @Title: selectUserInfoList
	 * @Description:
	 * @CreateTime 2018年8月7日上午10:37:15
	 * @Author ttsf-lfs
	 * @param num
	 * @param size
	 * @return
	 */
	@RequestMapping("/selectUserInfoList")
	public Result selectUserInfoList();
}
