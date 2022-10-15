package com.github.learn17.core.db.mapper.test;

import com.github.learn17.common.po.test.TblNewDemo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TblNewDemoMapper extends tk.mybatis.mapper.common.Mapper<TblNewDemo> {
    int updateBatch(List<TblNewDemo> list);

    int updateBatchSelective(List<TblNewDemo> list);

    int batchInsert(@Param("list") List<TblNewDemo> list);
}