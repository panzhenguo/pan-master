package com.pan.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pan.mybatis.BcMapper;
import com.pan.user.model.domain.UserOperatingLog;


@Mapper
public interface UserOperatingLogMapper extends BcMapper<UserOperatingLog> {
}