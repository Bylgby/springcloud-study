package com.example.shiro;

import com.alibaba.fastjson.JSON;
import com.example.common.base.SecurityToken;
import com.example.common.exception.HwException;
import com.example.common.lang.Result;
import com.example.common.utils.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * <br> shiro 过滤器
 * created date 2019/8/30 14:27
 *
 * @author maxiaowei
 */
@Slf4j
public class Auth2Filter extends FormAuthenticationFilter {

    public Auth2Filter(){
        this.setLoginUrl("/login");
    }

    /**
     * @Description     通过filter 封装原始token
     * @Date  2019/8/30 14:43
     * @Author maxiaowei
     **/
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        String channelType = servletRequest.getParameter("channelType");
        if (StringUtils.isEmpty(channelType)) {
            throw new HwException("channelType 为空！");
        }
        //获取请求token
        String token = getRequestToken((HttpServletRequest) servletRequest);

        if(StringUtils.isBlank(token)){
            return null;
        }
        SecurityToken securityToken = new SecurityToken(channelType);

        return securityToken;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token，如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) request);
        if(StringUtils.isBlank(token)){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());

            String json = JSON.toJSONString(Result.fail("invalid token"));

            httpResponse.getWriter().print(json);

            return false;
        }
        return executeLogin(request, response);
    }


    /**
     * @Description     获取请求token
     * @Date  2019/8/30 14:31
     * @Author maxiaowei
     **/
    private String getRequestToken(HttpServletRequest httpRequest){
        //从header中获取token
        String token = httpRequest.getHeader("token");

        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = httpRequest.getParameter("token");
        }
        return token;
    }
}
