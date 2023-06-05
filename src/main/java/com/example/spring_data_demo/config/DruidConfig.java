package com.example.spring_data_demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
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
        initParameters.put("allow", "127.0.0.1");


        // 设置初始化参数
        bean.setInitParameters(initParameters);
        return bean;
    }

}
