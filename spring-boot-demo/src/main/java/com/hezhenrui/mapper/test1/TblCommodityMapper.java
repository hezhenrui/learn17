package com.hezhenrui.mapper.test1;

import com.hezhenrui.domain.TblCommodity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author hzr
* @date 2022-01-08
*/
@Mapper
public interface TblCommodityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TblCommodity record);

    int insertOrUpdate(TblCommodity record);

    int insertOrUpdateSelective(TblCommodity record);

    int insertSelective(TblCommodity record);

    TblCommodity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TblCommodity record);

    int updateByPrimaryKey(TblCommodity record);

    int updateBatch(List<TblCommodity> list);

    int batchInsert(@Param("list") List<TblCommodity> list);
}