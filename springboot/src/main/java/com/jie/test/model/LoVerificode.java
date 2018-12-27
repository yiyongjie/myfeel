package com.jie.test.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
@ApiModel(value = "短信验证码")
public class LoVerificode {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 电话号码
     */
    @ApiModelProperty(value = "电话")
    private String telphone;
    /**
     * 存储时间戳
     */
    @ApiModelProperty(value = "存储时间戳")
    private Date timestamp;
    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    private String verificode;
}