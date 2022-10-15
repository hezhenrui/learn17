package com.github.learn17.module.demo.controller;

import com.github.learn17.common.entity.ResultVo;
import com.github.learn17.common.po.test.TblRocketTransactionEx;
import com.github.learn17.module.demo.service.RocketTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("rocketTransaction")
@RestController
public class RocketTransactionController {

    private final RocketTransactionService rocketTransactionService;

    public RocketTransactionController(RocketTransactionService rocketTransactionService) {
        this.rocketTransactionService = rocketTransactionService;
    }

    @PostMapping("transactionExResultVo")
    public ResultVo<TblRocketTransactionEx> transactionExResultVo(@RequestBody TblRocketTransactionEx tblRocketTransactionEx){
        return rocketTransactionService.sendTransaction(tblRocketTransactionEx);
    }
}
