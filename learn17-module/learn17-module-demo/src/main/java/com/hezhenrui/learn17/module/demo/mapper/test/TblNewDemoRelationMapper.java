package com.hezhenrui.learn17.module.demo.mapper.test;

import com.hezhenrui.learn17.module.demo.domain.test.TblNewDemoRelation;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TblNewDemoRelationMapper extends tk.mybatis.mapper.common.Mapper<TblNewDemoRelation> {
    int updateBatch(List<TblNewDemoRelation> list);

    int updateBatchSelective(List<TblNewDemoRelation> list);

    int batchInsert(@Param("list") List<TblNewDemoRelation> list);
}