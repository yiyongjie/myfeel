package com.jie.test.dao;

import com.jie.test.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {
    int insertSelective(SysUser record);
}