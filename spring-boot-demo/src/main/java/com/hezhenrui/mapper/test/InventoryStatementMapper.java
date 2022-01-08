package com.hezhenrui.mapper.test;

import com.hezhenrui.domain.InventoryStatement;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author hzr
* @date 2021-12-27
*/
@Mapper
public interface InventoryStatementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InventoryStatement record);

    int insertOrUpdate(InventoryStatement record);

    int insertOrUpdateSelective(InventoryStatement record);

    int insertSelective(InventoryStatement record);

    InventoryStatement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InventoryStatement record);

    int updateByPrimaryKey(InventoryStatement record);

    int updateBatch(List<InventoryStatement> list);

    int batchInsert(@Param("list") List<InventoryStatement> list);

    int reduceNumById(@Param("id")Integer id);


}