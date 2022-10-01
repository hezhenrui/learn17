package com.hezhenrui.demo.mapper.test;

import com.hezhenrui.demo.domain.test.TblNewDemoCopy;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TblNewDemoCopyMapper extends Mapper<TblNewDemoCopy> {
    int updateBatch(List<TblNewDemoCopy> list);

    int updateBatchSelective(List<TblNewDemoCopy> list);

    int batchInsert(@Param("list") List<TblNewDemoCopy> list);

    int deleteByPrimaryKey(Integer id);

    int insert(TblNewDemoCopy record);

    int insertSelective(TblNewDemoCopy record);

    TblNewDemoCopy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TblNewDemoCopy record);

    int updateByPrimaryKey(TblNewDemoCopy record);
}