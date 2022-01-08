package com.hezhenrui.controller;

import com.hezhenrui.domain.TblCommodity;
import com.hezhenrui.mapper.test1.TblCommodityMapper;
import com.hezhenrui.service.MybatisCaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hzr
 * @date 2021-12-24
 */
@RestController
@RequestMapping("mybatisCase")
public class MybatisCaseController {

    private final MybatisCaseService mybatisCaseService;

    private final TblCommodityMapper tblCommodityMapper;

    public MybatisCaseController(MybatisCaseService mybatisCaseService, TblCommodityMapper tblCommodityMapper) {
        this.mybatisCaseService = mybatisCaseService;
        this.tblCommodityMapper = tblCommodityMapper;
    }

    @GetMapping("mybatisFlushCase")
    public void mybatisFlushCase(){
        mybatisCaseService.mybatisFlushCase();
    }

    @GetMapping("multi")
    public TblCommodity multi(){
        return tblCommodityMapper.selectByPrimaryKey(1);
    }
}
