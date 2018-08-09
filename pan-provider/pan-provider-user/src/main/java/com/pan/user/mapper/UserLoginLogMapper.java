package com.pan.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pan.mybatis.BcMapper;
import com.pan.user.model.domain.UserLoginLog;


@Mapper
public interface UserLoginLogMapper extends BcMapper<UserLoginLog> {
}