package com.jie.test.service;


import com.jie.test.model.dto.UserDTO;

public interface LoVerificodeService {

    /**
     * 记录验证码
     * @param userDTO
     */
    void addVerificode(UserDTO userDTO);

    /**
     * 登录
     * @param userDTO
     */
    void login(UserDTO userDTO);
}
