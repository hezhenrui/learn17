package com.hezhenrui.mapper;

import com.hezhenrui.domain.Person;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author hzr
 * @date 2021-12-24
 */
@Mapper
public interface PersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertOrUpdate(Person record);

    int insertOrUpdateSelective(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);

    int updateBatch(List<Person> list);

    int batchInsert(@Param("list") List<Person> list);
}