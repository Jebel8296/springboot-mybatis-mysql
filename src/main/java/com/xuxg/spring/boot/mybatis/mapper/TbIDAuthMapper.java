package com.xuxg.spring.boot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.xuxg.spring.boot.mybatis.domain.TbIDAuth;

@Mapper
public interface TbIDAuthMapper {
	TbIDAuth selectById(int id);
}
