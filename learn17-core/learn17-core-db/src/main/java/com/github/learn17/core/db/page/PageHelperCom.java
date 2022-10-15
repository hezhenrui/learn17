package com.github.learn17.core.db.page;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * pagehelper拦截器
 */
@Component
public class PageHelperCom {

    /*
    设置PageHelper属性  不同数据源 自动配置Dialect
    */
    public void pageHelper(SqlSessionFactoryBean sqlSessionFactory){
        //分页插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "mysql");
        //是否将参数offset作为PageNum使用
        properties.setProperty("offsetAsPageNum", "true");
        //是否进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        //是否分页合理化
        // 注意的是reasonable参数，表示分页合理化，默认值为false。
        //如果该参数设置为 true 时，pageNum<=0 时会查询第一页，pageNum>pages（超过总数时），会查询最后一页。默认false 时，直接根据参数进行查询。
        properties.setProperty("reasonable", "false");
        interceptor.setProperties(properties);
        sqlSessionFactory.setPlugins(interceptor);
    }
}
