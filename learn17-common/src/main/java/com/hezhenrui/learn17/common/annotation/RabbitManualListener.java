package com.hezhenrui.learn17.common.annotation;

import com.hezhenrui.learn17.common.utils.ProceedingJoinPointParam;
import com.hezhenrui.learn17.common.utils.ProceedingJoinPointUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class RabbitManualListener {

    @Pointcut("@annotation(com.hezhenrui.learn17.common.annotation.RabbitManual)")
    private void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取Channel和Message参数
        ProceedingJoinPointParam pointParam = ProceedingJoinPointUtil.getParam(joinPoint);
        Message message = pointParam.getMessage();
        Channel channel = pointParam.getChannel();
        Object object = null;
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //业务逻辑执行
            object = joinPoint.proceed();
            // 采用手动应答模式, 手动确认应答更为安全稳定
            // 第一个参数是消息标识, 第二个是批量确认; false当前消息确认, true此次之前的消息确认
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                channel.basicReject(deliveryTag, false);
            } else {
                // 消费失败，消息重返队列
                channel.basicNack(deliveryTag, false, true);
            }
        }
        return object;
    }


}
