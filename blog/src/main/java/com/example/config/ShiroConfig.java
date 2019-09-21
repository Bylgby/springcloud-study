package com.example.config;

import cn.hutool.core.codec.Base64;
import com.example.shiro.Auth2Filter;
import com.example.shiro.OAuth2Realm;
import com.example.shiro.ShiroRedisCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;



/**
 * shiro 配置
 * <br>
 * created date 2019/8/30 10:55
 *
 * @author maxiaowei
 */
@Slf4j
@Configuration
public class ShiroConfig {

    @Resource
    private RedisTemplate redisTemplate;

    @Bean
    public OAuth2Realm oAuth2Realm(){
        return new OAuth2Realm();
    }

    @Bean
    public ShiroRedisCacheManager redisCacheManager() {
        ShiroRedisCacheManager redisCacheManager = new ShiroRedisCacheManager(redisTemplate);
        return redisCacheManager;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 设置session超时时间，单位为毫秒
        sessionManager.setGlobalSessionTimeout(10000L);
        sessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
        return sessionManager;
    }

    @Bean("securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(redisCacheManager());
        // 设置realm
        securityManager.setRealm(oAuth2Realm());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        securityManager.setRememberMeManager(rememberMeManager());
        log.info("securityManager --------------------------->初始化");
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        //没有登录的用户只能访问登陆页面
        shiroFilter.setLoginUrl("/login");
        // 登录成功要跳转的路径
        shiroFilter.setSuccessUrl("/index");
        //oauth过滤,自定义拦截器
        Map<String, Filter> filters = new HashMap<>();
        filters.put("oauth2", new Auth2Filter());
        shiroFilter.setFilters(filters);
        // 权限控制map
        Map<String,String> map = new HashMap<>();
        map.put("/login", "anon");
        map.put("/js/**", "anon");
        map.put("/css/**", "anon");
        map.put("/logout", "logout");

        map.put("/**", "authc"); //需要登录权限
        shiroFilter.setFilterChainDefinitionMap(map);
        return shiroFilter;
    }

    //--------- 开启注解 -----------
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
//
//    /**
//     * @Description     授权所用配置
//     * @Date  2019/8/30 17:07
//     * @Author maxiaowei
//     **/
//    @Bean
//    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
//        creator.setProxyTargetClass(true);
//        return creator;
//    }

    /**
     * Shiro生命周期处理器
     *
     */
//    @BeanLifecycleBeanPostProcessor
//    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }

    /**
     * rememberMe cookie 效果是重开浏览器后无需重新登录
     *
     * @return SimpleCookie
     */
    private SimpleCookie rememberMeCookie() {
        // 这里的Cookie的默认名称是 CookieRememberMeManager.DEFAULT_REMEMBER_ME_COOKIE_NAME
        SimpleCookie cookie = new SimpleCookie(CookieRememberMeManager.DEFAULT_REMEMBER_ME_COOKIE_NAME);
        // 是否只在https情况下传输
        cookie.setSecure(false);
        // 设置 cookie 的过期时间，单位为秒，这里为一天
        cookie.setMaxAge(60*60*24);
        return cookie;
    }

    /**
     * cookie管理对象
     *
     * @return CookieRememberMeManager
     */
    private CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // rememberMe cookie 加密的密钥
        cookieRememberMeManager.setCipherKey(Base64.decode("ZWvohmPdUsAWT3=KpPqda"));
        return cookieRememberMeManager;
    }


}
