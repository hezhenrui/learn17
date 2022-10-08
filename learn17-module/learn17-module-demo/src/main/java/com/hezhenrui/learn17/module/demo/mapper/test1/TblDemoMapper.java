package com.hezhenrui.learn17.module.demo.mapper.test1;

import com.hezhenrui.learn17.module.demo.domain.test1.TblDemo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TblDemoMapper extends tk.mybatis.mapper.common.Mapper<TblDemo> {
    int batchInsert(@Param("list") List<TblDemo> list);
}