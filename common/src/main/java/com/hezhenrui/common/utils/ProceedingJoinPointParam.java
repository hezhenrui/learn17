package com.hezhenrui.common.utils;

import com.rabbitmq.client.Channel;
import lombok.Data;
import org.springframework.amqp.core.Message;

import java.io.Serializable;

@Data
public class ProceedingJoinPointParam implements Serializable {

    private Message message;

    private Channel channel;

}
