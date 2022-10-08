package com.hezhenrui.learn17.module.demo.service;

import com.hezhenrui.learn17.common.entity.ResultVo;
import com.hezhenrui.learn17.module.demo.domain.test.TblRocketTransactionEx;

public interface RocketTransactionService {

    ResultVo<TblRocketTransactionEx> sendTransaction(TblRocketTransactionEx tblRocketTransactionEx);

}
