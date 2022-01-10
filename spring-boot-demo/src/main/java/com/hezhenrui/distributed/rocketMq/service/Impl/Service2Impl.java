package com.hezhenrui.distributed.rocketMq.service.Impl;

import com.hezhenrui.distributed.rocketMq.service.Service2;
import com.hezhenrui.domain.TblCommodity;
import org.springframework.stereotype.Service;

/**
 * @author hzr
 * @date 2022-01-10
 */
@Service
public class Service2Impl implements Service2 {
    @Override
    public void exception() {
        TblCommodity tblCommodity = new TblCommodity();
        System.out.println(tblCommodity.getCount() > 0);
    }
}
