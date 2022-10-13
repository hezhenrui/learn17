package com.hezhenrui.learn17.common.utils;

import com.hezhenrui.learn17.common.entity.ResultVo;
import com.hezhenrui.learn17.common.enums.ResultEnum;

public class ResultUtil<T> {

    @SuppressWarnings("rawtypes")
    public static ResultVo success() {
        return success(null);
    }
    @SuppressWarnings("rawtypes")
    public static <T> ResultVo success(T data) {
        return ResultVo.builder().status(ResultEnum.SUCCESS.getStatus()).msg(ResultEnum.SUCCESS.getMsg()).data(data).build();
    }
    @SuppressWarnings("rawtypes")
    public static <T> ResultVo error(T data) {
        return ResultVo.builder().status(ResultEnum.ERROR.getStatus()).msg(ResultEnum.ERROR.getMsg()).data(data).build();
    }
    @SuppressWarnings("rawtypes")
    public static <T> ResultVo build(ResultEnum resultEnum, T data) {
        return ResultVo.builder().status(resultEnum.getStatus()).msg(resultEnum.getMsg()).data(data).build();
    }

}
