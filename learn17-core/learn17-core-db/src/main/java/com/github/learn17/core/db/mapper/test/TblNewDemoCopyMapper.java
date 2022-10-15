package com.github.learn17.core.db.mapper.test;

import com.github.learn17.common.po.test.TblNewDemoCopy;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TblNewDemoCopyMapper extends tk.mybatis.mapper.common.Mapper<TblNewDemoCopy> {
    int updateBatch(List<TblNewDemoCopy> list);

    int updateBatchSelective(List<TblNewDemoCopy> list);

    int batchInsert(@Param("list") List<TblNewDemoCopy> list);
}