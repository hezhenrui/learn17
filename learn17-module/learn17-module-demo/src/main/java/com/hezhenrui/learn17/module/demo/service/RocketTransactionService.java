package com.hezhenrui.learn17.module.demo.service;

import com.hezhenrui.learn17.common.entity.ResultVo;
import com.hezhenrui.learn17.common.po.test.TblRocketTransactionEx;

public interface RocketTransactionService {

    ResultVo<TblRocketTransactionEx> sendTransaction(TblRocketTransactionEx tblRocketTransactionEx);

}
