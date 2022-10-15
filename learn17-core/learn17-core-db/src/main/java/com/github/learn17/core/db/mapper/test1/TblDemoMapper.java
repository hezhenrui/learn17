package com.github.learn17.core.db.mapper.test1;

import com.github.learn17.common.po.test1.TblDemo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TblDemoMapper extends tk.mybatis.mapper.common.Mapper<TblDemo> {
    int batchInsert(@Param("list") List<TblDemo> list);
}