package com.jie.test.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "返回类")
public class APIResponse<T> {
    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "数据")
    private T data;
    @ApiModelProperty(value = "结果")
    private String msg;
}
