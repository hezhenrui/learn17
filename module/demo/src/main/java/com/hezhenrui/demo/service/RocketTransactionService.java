package com.hezhenrui.demo.service;

import com.hezhenrui.common.entity.ResultVo;
import com.hezhenrui.demo.domain.test.TblRocketTransactionEx;

public interface RocketTransactionService {

    ResultVo<TblRocketTransactionEx> sendTransaction(TblRocketTransactionEx tblRocketTransactionEx);

}
