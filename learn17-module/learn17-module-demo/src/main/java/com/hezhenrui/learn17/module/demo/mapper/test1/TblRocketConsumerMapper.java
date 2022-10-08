package com.hezhenrui.learn17.module.demo.mapper.test1;

import com.hezhenrui.learn17.module.demo.domain.test1.TblRocketConsumer;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TblRocketConsumerMapper extends tk.mybatis.mapper.common.Mapper<TblRocketConsumer> {
    int updateBatch(List<TblRocketConsumer> list);

    int updateBatchSelective(List<TblRocketConsumer> list);

    int batchInsert(@Param("list") List<TblRocketConsumer> list);
}