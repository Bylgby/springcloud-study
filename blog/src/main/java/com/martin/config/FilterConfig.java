package com.martin.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * <br>
 * created date 2019/8/30 16:03
 *
 * @author maxiaowei
 */

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean shiroFilterRegistion(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 把shiroFilter 注册进来
        registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //该值缺省为false，表示生命周期由SpringApplicationContext管理，设置为true则表示由ServletContainer管理
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setEnabled(true);
        registration.setOrder(Integer.MAX_VALUE - 1);
        registration.addUrlPatterns("/*");
        return registration;
    }
}
