package com.hezhenrui.demo.mapper.test;

import com.hezhenrui.demo.domain.test.TblNewDemo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TblNewDemoMapper extends Mapper<TblNewDemo> {
    int updateBatch(List<TblNewDemo> list);

    int updateBatchSelective(List<TblNewDemo> list);

    int batchInsert(@Param("list") List<TblNewDemo> list);
}