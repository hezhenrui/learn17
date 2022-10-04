package com.hezhenrui.consumer.mapper.test;

import com.hezhenrui.consumer.domain.test.TblNewDemo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TblNewDemoMapper extends tk.mybatis.mapper.common.Mapper<TblNewDemo> {
    int updateBatch(List<TblNewDemo> list);

    int updateBatchSelective(List<TblNewDemo> list);

    int batchInsert(@Param("list") List<TblNewDemo> list);
}