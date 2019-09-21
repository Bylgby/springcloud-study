package com.martin.comfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 关掉对/eureka/*路径下的检查
 * <br>
 * created date 2019/9/19 14:25
 *
 * @author maxiaowei
 */
@Configuration
@EnableWebSecurity
public class WebSecurtyConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity  auth) throws Exception {
        // 关闭csrf
        auth.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(auth);
    }
}
