package com.github.learn17.common.enums;

public enum ResultEnum {

    SUCCESS(200,"操作成功"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    ERROR(500,"操作失败");

    //响应业务状态
    private Integer status;
    //响应消息
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResultEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

}
