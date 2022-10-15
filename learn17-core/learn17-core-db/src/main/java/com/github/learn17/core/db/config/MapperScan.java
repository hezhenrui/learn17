package com.github.learn17.core.db.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.github.learn17.core.db.mapper.*")
public class MapperScan {
}
