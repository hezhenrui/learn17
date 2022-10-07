package com.hezhenrui.common.utils;

import com.hezhenrui.common.entity.ResultVo;
import com.hezhenrui.common.enums.ResultEnum;

public class ResultUtil<T> {

    public static <T> ResultVo success() {
        return success(null);
    }

    public static <T> ResultVo success(T data) {
        return ResultVo.builder().status(ResultEnum.SUCCESS.getStatus()).msg(ResultEnum.SUCCESS.getMsg()).data(data).build();
    }

    public static <T> ResultVo error(T data) {
        return ResultVo.builder().status(ResultEnum.ERROR.getStatus()).msg(ResultEnum.ERROR.getMsg()).data(data).build();
    }

    public static <T> ResultVo build(ResultEnum resultEnum, T data) {
        return ResultVo.builder().status(resultEnum.getStatus()).msg(resultEnum.getMsg()).data(data).build();
    }

}
