package com.github.learn17.module.demo.config;

import com.github.learn17.common.config.BaseSwaggerConfig;
import com.github.learn17.common.entity.SwaggerProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration extends BaseSwaggerConfig {

    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandler(WebEndpointsSupplier webEndpointsSupplier,
                                                                  ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier,
                                                                  EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties,
                                                                  WebEndpointProperties webEndpointProperties, Environment environment) {
        return webEndpointServletHandlerMapping(webEndpointsSupplier, servletEndpointsSupplier, controllerEndpointsSupplier, endpointMediaTypes, corsProperties, webEndpointProperties, environment);
    }

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.github.learn17.module.demo")
                .title("demo")
                .contactEmail("zhenrui.he.code@outlook.com")
                .contactUrl("https://github.com/hezhenrui/learn17")
                .contactName("hezhenrui")
                .version("1.0.0")
                .build();
    }

}
