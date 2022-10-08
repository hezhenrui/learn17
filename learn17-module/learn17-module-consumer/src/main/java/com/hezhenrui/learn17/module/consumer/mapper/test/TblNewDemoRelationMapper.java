package com.hezhenrui.learn17.module.consumer.mapper.test;

import com.hezhenrui.learn17.module.consumer.domain.test.TblNewDemoRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TblNewDemoRelationMapper extends tk.mybatis.mapper.common.Mapper<TblNewDemoRelation> {
    int updateBatch(List<TblNewDemoRelation> list);

    int updateBatchSelective(List<TblNewDemoRelation> list);

    int batchInsert(@Param("list") List<TblNewDemoRelation> list);
}