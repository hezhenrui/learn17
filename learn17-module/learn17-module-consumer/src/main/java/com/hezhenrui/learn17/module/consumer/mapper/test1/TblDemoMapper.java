package com.hezhenrui.learn17.module.consumer.mapper.test1;


import com.hezhenrui.learn17.module.consumer.domain.test1.TblDemo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TblDemoMapper extends tk.mybatis.mapper.common.Mapper<TblDemo> {
    int batchInsert(@Param("list") List<TblDemo> list);
}