package com.hezhenrui.controller;

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

    public MybatisCaseController(MybatisCaseService mybatisCaseService) {
        this.mybatisCaseService = mybatisCaseService;
    }

    @GetMapping("mybatisFlushCase")
    public void mybatisFlushCase(){
        mybatisCaseService.mybatisFlushCase();
    }
}
