package com.jie.test.dao;

import com.jie.test.model.LoVerificode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoVerificodeMapper {

    int countVerificode(String telphone);

    int insertSelective(LoVerificode record);

    int updateVerificode(LoVerificode record);

    LoVerificode getVerificodeByTelphone(LoVerificode record);
}