package com.jie.test.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "用户登录token")
public class UserToken {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "手机号")
    private String telphone;
    /**
     * 用户token
     */
    @ApiModelProperty(value = "用户token")
    private String userToken;
    /**
     * token创建时间
     */
    @ApiModelProperty(value = "token创建时间")
    private Date createDate;

}