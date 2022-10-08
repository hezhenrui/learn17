package com.hezhenrui.learn17.core.db.page;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.autoconfigure.MapperProperties;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * exclude = {DataSourceAutoConfiguration.class} 重新用 CustomizedMapperAutoConfiguration
 */
@Configuration
@ConditionalOnBean(SqlSessionFactory.class)
@EnableConfigurationProperties(MapperProperties.class)
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class CustomizedMapperAutoConfiguration {

    private final List<SqlSessionFactory> sqlSessionFactoryList;

    private final MapperProperties properties;

    public CustomizedMapperAutoConfiguration(List<SqlSessionFactory> sqlSessionFactoryList, MapperProperties properties) {
        this.sqlSessionFactoryList = sqlSessionFactoryList;
        this.properties = properties;
    }

    @PostConstruct
    public void addPageInterceptor() {
        MapperHelper mapperHelper = new MapperHelper();
        mapperHelper.setConfig(properties);
        if (properties.getMappers().size() > 0) {
            for (Class mapper : properties.getMappers()) {
                mapperHelper.registerMapper(mapper);
            }
        } else {
            mapperHelper.registerMapper(Mapper.class);
        }
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            mapperHelper.processConfiguration(sqlSessionFactory.getConfiguration());
        }
    }

}
