package com.jie.test.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@ApiModel(value = "用户登陆")
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -2421304342856649130L;

    /**
     * 电话号码
     */
    @ApiModelProperty(value = "电话号码")
    private String telphone;
    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    private String verificode;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private String timeStamp;

    /**
     * token
     */
    @ApiModelProperty(value = "token")
    private String token;
}
