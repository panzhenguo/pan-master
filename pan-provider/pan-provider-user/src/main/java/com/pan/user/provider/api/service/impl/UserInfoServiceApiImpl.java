package com.pan.user.provider.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pan.common.utils.MD5Utils;
import com.pan.core.Result;
import com.pan.core.ResultGenerator;
import com.pan.user.mapper.UserInfoMapper;
import com.pan.user.mapper.UserLoginLogMapper;
import com.pan.user.model.domain.UserInfo;
import com.pan.user.model.domain.UserLoginLog;
import com.pan.user.model.vo.UserInfoVo;
import com.pan.user.provider.api.service.UserInfoServiceApi;

import tk.mybatis.mapper.entity.Example;


@RestController
@Transactional
public class UserInfoServiceApiImpl implements UserInfoServiceApi {

	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	UserLoginLogMapper userLoginMapper;


	@Override
	@Transactional
	public Result updateUserRoleIdsByUserInfoId(UserInfoVo vo) {
		if(vo!=null&&vo.getUserInfo()!=null) {
			if(vo.getUpdateRoleIdsType()==1) {
				// 添加角色信息
				userInfoMapper.updateAddUserInfoRoleIds(vo.getUserInfo().getUserRoleIds(),vo.getUserInfo().getId());
				return ResultGenerator.getSuccessResult();
			}else {
				// 删除角色信息 循环删除 角色信息
				String [] roleIdsArr=vo.getUserInfo().getUserRoleIds().split(",");
				for (int i = 0; i < roleIdsArr.length; i++) {
					userInfoMapper.updataDelUserInfoRoleIds( roleIdsArr[i],vo.getUserInfo().getId());
				}
				return ResultGenerator.getSuccessResult();
			}
		}
		return ResultGenerator.getFailResultByServiceApiParm();
	}

	

	@Override
	public Result checkUserName(@PathVariable("userName") String userName) {
		if(userName!=null) {
			UserInfo ui = new UserInfo();
			ui.setUserName(userName);
			Example ex = new Example(UserInfo.class);
			ex.createCriteria().andEqualTo("userName", userName);
			int selectCountByExample = userInfoMapper.selectCountByExample(ex);
			return ResultGenerator.getSuccessResult(selectCountByExample);
		}
		return ResultGenerator.getFailResultByServiceApiParm();
	}


	@Override
	public Result changeUserInfoStatus(@PathVariable("userId") int userId,@PathVariable("changeType") int changeType) {
		if(userId>0) {
			UserInfo ui= new UserInfo();
			ui.setId(userId);
			ui.setUserStatus(changeType);
			userInfoMapper.updateByPrimaryKeySelective(ui);
			return ResultGenerator.getSuccessResult();
		}
		return ResultGenerator.getFailResult("参数缺失！");
	}

	@Override
	public Result updateUserInfoByUserId(UserInfoVo vo) {
		if(vo==null) {
			return ResultGenerator.getFailResult("参数缺失！");
		}
		if(vo.getUpdateUserInfoPwdIsChange()==1) {
			// 本次修改密码发生了变动
			String safePdw= MD5Utils.getMD5String(vo.getUserInfo()+vo.getUserInfo().getPwdSalt());
			vo.getUserInfo().setUserPwd(safePdw);
		}
		userInfoMapper.updateByPrimaryKey(vo.getUserInfo());
		return ResultGenerator.getSuccessResult();
	}
	
	
	@Override
	public Result userLogin(UserInfoVo vo) {
		if (vo == null||vo.getUserInfo()==null||vo.getUserLoginLog()==null) {
			return ResultGenerator.getFailResultByServiceApiParm();
		}
		UserInfo userInfo = vo.getUserInfo();
		UserLoginLog ull= vo.getUserLoginLog();
		Example ex= new Example(UserInfo.class);
		ex.createCriteria().andEqualTo("userName", userInfo.getUserName());
		
		UserInfo ru = userInfoMapper.selectOneByExample(ex);
		if (ru!=null) {
			if(ru.getUserStatus()!=1) {
				return ResultGenerator.getFailResult("该账户存在异常，请联系管理员！");
			}
			// 判断密码的合法性
			String safePwd = MD5Utils.getMD5String(userInfo.getUserPwd() + ru.getPwdSalt());
			if (ru.getUserPwd().equals(safePwd)) {
				ull.setLoginDate(new Date());
				userLoginMapper.insert(ull);
				return ResultGenerator.getSuccessResult(ru);
			}
		}
		return ResultGenerator.getFailResult("账号或密码错误！");
	}

	@Override
	public Result userUpdatePwd(UserInfo userInfo) {
		if(userInfo==null) {
			return ResultGenerator.getFailResultByServiceApiParm();
		}
		UserInfo ru = new UserInfo();
		ru.setId(userInfo.getId());
		String safePwd = MD5Utils.getMD5String(userInfo.getPwdSalt()+userInfo.getUserPwd());
		
		ru.setUserPwd(safePwd);
		
		userInfoMapper.updateByPrimaryKeySelective(ru);
		
		return ResultGenerator.getSuccessResult();
	}

	@Override
	public Result selectUserInfoById(@PathVariable("id") int id) {
		if (id > 0) {
			UserInfo ru = userInfoMapper.selectByPrimaryKey(id);
			return ResultGenerator.getSuccessResult(ru);
		}
		return ResultGenerator.getFailResult("参数错误");
	}

	@Override
	public Result addUserInfo(UserInfo userInfo) {
		if (userInfo != null ) {
			// 业务处理
			userInfo.setCreateDate(new Date());
			userInfo.setCreateUserId(0);
			userInfo.setPwdSalt(UUID.randomUUID().toString().replaceAll("-", ""));
			userInfo.setUserStatus(1);
			userInfoMapper.insertSelective(userInfo);
			return ResultGenerator.getSuccessResult();
		}
		return ResultGenerator.getFailResult("参数错误");
	}


	@Override
	public Result deleteUserInfoByUserId(@PathVariable("id") int id) {
		if (id > 0) {
			UserInfo ui = new UserInfo();
			ui.setId(id);
			ui.setUserStatus(-1);
			userInfoMapper.updateByPrimaryKeySelective(ui);
			return ResultGenerator.getSuccessResult();
		}
		return ResultGenerator.getFailResult("参数缺失！");
	}


	@Override
	public Result selectUserInfoByRoleIds(UserInfo userInfo) {
	List<UserInfo> list = userInfoMapper.selectUserInfoByRoleIds(userInfo);
		return ResultGenerator.getSuccessResult(list);
	}



	@Override
	public Result selectUserInfoListByPage(@PathVariable("num") int num,@PathVariable("size") int size) {
		PageHelper.startPage(num, size);
		List<UserInfo> list= userInfoMapper.selectAll();
		PageInfo<UserInfo> page= new PageInfo<>(list);
		return ResultGenerator.getSuccessResult(page);
	}



	@Override
	public Result selectUserInfoList() {
		List<UserInfo> list= userInfoMapper.selectByList();
		return ResultGenerator.getSuccessResult(list);
	}

}
