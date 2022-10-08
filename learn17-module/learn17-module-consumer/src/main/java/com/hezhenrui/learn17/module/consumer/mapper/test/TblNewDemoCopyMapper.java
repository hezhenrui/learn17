package com.hezhenrui.learn17.module.consumer.mapper.test;

import com.hezhenrui.learn17.module.consumer.domain.test.TblNewDemoCopy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TblNewDemoCopyMapper extends tk.mybatis.mapper.common.Mapper<TblNewDemoCopy> {
    int updateBatch(List<TblNewDemoCopy> list);

    int updateBatchSelective(List<TblNewDemoCopy> list);

    int batchInsert(@Param("list") List<TblNewDemoCopy> list);
}