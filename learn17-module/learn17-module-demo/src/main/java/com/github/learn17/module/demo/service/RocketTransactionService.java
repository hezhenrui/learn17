package com.github.learn17.module.demo.service;

import com.github.learn17.common.entity.ResultVo;
import com.github.learn17.common.po.test.TblRocketTransactionEx;

public interface RocketTransactionService {

    ResultVo<TblRocketTransactionEx> sendTransaction(TblRocketTransactionEx tblRocketTransactionEx);

}
