package com.hezhenrui.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author hzr
 * @date 2021-12-24
 */
public interface MybatisCaseService {

    @Transactional(rollbackFor = Exception.class)
    void mybatisFlushCase();
}
