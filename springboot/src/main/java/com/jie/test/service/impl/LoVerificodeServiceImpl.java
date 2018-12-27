package com.jie.test.service.impl;

import com.jie.test.common.exception.AppException;
import com.jie.test.common.model.APIResponse;
import com.jie.test.common.util.Md5Util;
import com.jie.test.common.util.SmsUtil;
import com.jie.test.dao.LoVerificodeMapper;
import com.jie.test.dao.SysUserMapper;
import com.jie.test.dao.UserTokenMapper;
import com.jie.test.model.LoVerificode;
import com.jie.test.model.UserToken;
import com.jie.test.model.dto.UserDTO;
import com.jie.test.service.LoVerificodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class LoVerificodeServiceImpl implements LoVerificodeService {

    @Autowired
    private LoVerificodeMapper loVerificodeMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;
    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public void addVerificode(UserDTO userDTO) {
        if (userDTO == null) {
            throw new AppException(APIResponse.FAIL, "对象不能为空");
        }
        if (StringUtils.isBlank(userDTO.getTelphone())) {
            throw new AppException(APIResponse.FAIL, "电话号码不能为空");
        }
        int ver = SmsUtil.sms(userDTO.getTelphone());
        int count = loVerificodeMapper.countVerificode(userDTO.getTelphone());
        LoVerificode loVerificode = new LoVerificode();
        loVerificode.setTelphone(userDTO.getTelphone());
        loVerificode.setTimestamp(new Date());
        loVerificode.setVerificode(String.valueOf(ver));
        if (count == 0) {
            loVerificodeMapper.insertSelective(loVerificode);
        } else {
            loVerificodeMapper.updateVerificode(loVerificode);
        }
    }

    @Override
    public void login(UserDTO userDTO) {
        if (userDTO == null) {
            throw new AppException(APIResponse.FAIL, "对象不能为空");
        }
        if (StringUtils.isBlank(userDTO.getTelphone())) {
            throw new AppException(APIResponse.FAIL, "电话号码不能为空");
        }
        if (StringUtils.isBlank(userDTO.getVerificode())) {
            throw new AppException(APIResponse.FAIL, "验证码不能为空");
        }
        LoVerificode loVerificode = new LoVerificode();
        loVerificode.setTelphone(userDTO.getTelphone());
        loVerificode = loVerificodeMapper.getVerificodeByTelphone(loVerificode);
        long cur = System.currentTimeMillis();
        long cre = loVerificode.getTimestamp().getTime();
        long userTime = (cur - cre) / 1000;
        if (userTime > 120) {
            throw new AppException(APIResponse.FAIL, "时间超时请重新获取验证码");
        }
        if (userDTO.getVerificode().equals(loVerificode.getVerificode())) {
            UserToken userToken = new UserToken();
            userToken.setCreateDate(new Date());
            userToken.setTelphone(userDTO.getTelphone());
            String token = Md5Util.getMD5(userDTO.getTelphone() + System.currentTimeMillis());
            userToken.setUserToken(token);
            userTokenMapper.insertSelective(userToken);
            System.out.println("登录成功");
        } else {
            throw new AppException(APIResponse.FAIL, "验证码错误");
        }
    }
}
