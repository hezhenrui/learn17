package com.hezhenrui.demo.mapper.test;

import com.hezhenrui.demo.domain.test.TblNewDemoRelation;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface TblNewDemoRelationMapper extends Mapper<TblNewDemoRelation> {
    int updateBatch(List<TblNewDemoRelation> list);

    int updateBatchSelective(List<TblNewDemoRelation> list);

    int batchInsert(@Param("list") List<TblNewDemoRelation> list);
}