package com.jie.test.dao;

import com.jie.test.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User getUserById(Long id);
}
