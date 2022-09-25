package com.hezhenrui.demo.config.multi;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MultiDataSourceConfig {

    //主数据源 ds1数据源
    @Primary
    @Bean(name = "ds1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public HikariDataSource ds1DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    //第二个ds2数据源
    @Bean(name = "ds2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ds2")
    public HikariDataSource ds2DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
