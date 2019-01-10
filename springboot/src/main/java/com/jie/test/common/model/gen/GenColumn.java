package com.jie.test.common.model.gen;

import lombok.Data;

/**
 * 生成对象字段模板
 */
@Data
public class GenColumn {
    /**
     * 字段名称
     */
    private String column;
    /**
     * 生成模板字段
     */
    private String modelColumn;
    /**
     * 字段类型
     */
    private String colunmType;
    /**
     * 字段注释
     */
    private String columnRemark;
}
