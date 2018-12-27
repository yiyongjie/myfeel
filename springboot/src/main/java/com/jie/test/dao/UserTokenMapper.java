package com.jie.test.dao;

import com.jie.test.model.UserToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTokenMapper {

    int insertSelective(UserToken record);

    UserToken getUserTokenByMobile(String mobile);
}