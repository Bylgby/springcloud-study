package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * @Description     初始化配置
 * @Author maxiaowei
 * @create 2019/4/22 14:57
 * @Version 1.0
 **/
@Component
@Slf4j
@Order(1000)
public class ContextStartup implements ApplicationRunner, ServletContextAware {

    @Autowired
    ServletContext servletContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.setAttribute("base",servletContext.getContextPath());
    }
}
