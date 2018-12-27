package com.jie.test.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "用户")
public class SysUser {
    /**
     * 用户
     */
    @ApiModelProperty(value = "id")
    private String id;
    /**
     * 归属公司
     */
    @ApiModelProperty(value = "归属公司")
    private String companyId;
    /**
     * 归属部门
     */
    @ApiModelProperty(value = "归属部门")
    private String officeId;
    /**
     * 登录名
     */
    @ApiModelProperty(value = "用户名")
    private String loginName;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 工号
     */
    @ApiModelProperty(value = "工号")
    private String no;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String name;
    /**
     * emali
     */
    @ApiModelProperty(value = "email")
    private String email;
    /**
     * 电话
     */
    @ApiModelProperty(value ="电话")
    private String phone;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型")
    private String userType;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String photo;
    /**
     * 最后登录ip
     */
    @ApiModelProperty(value = "最后登录ip")
    private String loginIp;
    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    private Date loginDate;
    /**
     * 是否可以登录
     */
    @ApiModelProperty(value = "是否可以登录")
    private String loginFlag;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updateBy;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateDate;
    /**
     * 标记
     */
    @ApiModelProperty(value = "标记")
    private String remarks;
    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记")
    private String delFlag;
    /**
     * 供应商id
     */
    @ApiModelProperty(value = "供应商id")
    private String supplierId;
    /**
     * 采购商id
     */
    @ApiModelProperty(value = "采购商id")
    private String purchaseId;
    /**
     * 不知道是什么
     */
    @ApiModelProperty(value = "不知道是什么")
    private String ltdflag;

}