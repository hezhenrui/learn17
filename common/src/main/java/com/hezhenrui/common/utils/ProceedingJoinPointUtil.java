package com.hezhenrui.common.utils;

import com.rabbitmq.client.Channel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.amqp.core.Message;

public class ProceedingJoinPointUtil {

    /**
     * 获取切入param
     */
    public static ProceedingJoinPointParam getParam(ProceedingJoinPoint proceedingJoinPoint) {
        ProceedingJoinPointParam proceedingJoinPointParam = new ProceedingJoinPointParam();
        Object[] values = proceedingJoinPoint.getArgs();
        for (Object value : values) {
            if (value instanceof Channel) {
                proceedingJoinPointParam.setChannel((Channel) value);
            } else if (value instanceof Message) {
                proceedingJoinPointParam.setMessage((Message) value);
            }
        }
        return proceedingJoinPointParam;
    }
}
