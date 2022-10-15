package com.github.learn17.api.demo;

import com.github.learn17.common.constants.ServiceNameConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = ServiceNameConstant.DEMO,path = "/demo/kafka")
public interface KafkaClient {

    @PostMapping("send")
    public void send();
}
