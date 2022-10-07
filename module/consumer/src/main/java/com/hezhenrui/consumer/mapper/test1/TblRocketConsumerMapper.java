package com.hezhenrui.consumer.mapper.test1;

import com.hezhenrui.consumer.domain.test1.TblRocketConsumer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TblRocketConsumerMapper extends tk.mybatis.mapper.common.Mapper<TblRocketConsumer> {
    int updateBatch(List<TblRocketConsumer> list);

    int updateBatchSelective(List<TblRocketConsumer> list);

    int batchInsert(@Param("list") List<TblRocketConsumer> list);
}