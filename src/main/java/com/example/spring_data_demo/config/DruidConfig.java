package com.example.spring_data_demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {
    @Bean
    public DataSource druidDataSource() {

        /*
        * new 只会读取system.properties
        * return new DruidDataSource();
        * */
        // 会主动查找datasource.druid jdbc properties
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 后台监控，相当于web.xml
     * 因为springBoot内置了servlet容器，所以没有web.xml，替代方法：servletRegistrationBean
     *
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        HashMap<String, String> initParameters = new HashMap<>();

        // 增加配置。登录的key是固定的
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");

        // 允许访问的对象
        initParameters.put("allow", "");


        // 设置初始化参数
        bean.setInitParameters(initParameters);
        return bean;
    }

}
