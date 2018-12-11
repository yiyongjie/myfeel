package com.jie.test.dao;

import com.jie.test.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    @Select("select * from user where id =#{id}")
    User getUserById(Long id);
}
