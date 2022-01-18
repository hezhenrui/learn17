package com.hezhenrui.distributed.kafka;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author hzr
 * @date 2022-01-18
 */
@Aspect
public class KafkaPartitionOffsetAspect {

    @Pointcut("@annotation(com.hezhenrui.distributed.kafka.KafkaPartitionOffset)")
    public void pointcut(){
    }

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {
    }

}
