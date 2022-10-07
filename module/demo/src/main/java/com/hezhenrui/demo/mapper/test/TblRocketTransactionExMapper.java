package com.hezhenrui.demo.mapper.test;

import com.hezhenrui.demo.domain.test.TblRocketTransactionEx;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TblRocketTransactionExMapper extends tk.mybatis.mapper.common.Mapper<TblRocketTransactionEx> {
    int updateBatch(List<TblRocketTransactionEx> list);

    int updateBatchSelective(List<TblRocketTransactionEx> list);

    int batchInsert(@Param("list") List<TblRocketTransactionEx> list);
}