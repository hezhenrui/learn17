package com.hezhenrui.common.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class ResultVo<T> implements Serializable {

    //响应业务状态
    private Integer status;
    //响应业务消息
    private String msg;
    //响应业务数据
    private T data;

}
