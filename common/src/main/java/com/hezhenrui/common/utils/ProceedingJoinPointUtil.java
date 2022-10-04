package com.hezhenrui.common.utils;

import com.rabbitmq.client.Channel;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.HashMap;
import java.util.Map;

public class ProceedingJoinPointUtil {

    /**
     * 获取切入param
     */
    public static Map<String, Object> getParam(ProceedingJoinPoint proceedingJoinPoint) {
        Map<String, Object> map = new HashMap<>();
        Object[] values = proceedingJoinPoint.getArgs();
        for (Object value : values) {
            if (value instanceof Channel) {
                map.put("Channel", value);
            } else {
                map.put(value.getClass().getSimpleName(), value);
            }
        }
        return map;
    }
}
