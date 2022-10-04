package com.hezhenrui.consumer.mapper.test1;


import com.hezhenrui.consumer.domain.test1.TblDemo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TblDemoMapper extends tk.mybatis.mapper.common.Mapper<TblDemo> {
    int batchInsert(@Param("list") List<TblDemo> list);
}