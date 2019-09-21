package com.example.shiro;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpStatus;
import com.example.common.base.LoginInfo;
import com.example.common.base.SecurityToken;
import com.example.common.exception.HwException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * PC 源
 * <br>
 * created date 2019/8/30 10:53
 *
 * @author maxiaowei
 */
@Component
@Slf4j
public class OAuth2Realm extends AuthorizingRealm {

    /**
     * @Description     授权
     * @Date  2019/8/30 10:55
     * @Author maxiaowei
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 当前用户
        Object principal = principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        authorizationInfo.addRole("auth2");
        authorizationInfo.addStringPermission("auth2:info");

        return authorizationInfo;
    }


    /**
     * @Description     认证
     * @Date  2019/8/30 10:55
     * @Author maxiaowei
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        SecurityToken token = (SecurityToken) authenticationToken;

        if (StringUtils.isEmpty(token.getUsername())) {
            throw new HwException(HttpStatus.HTTP_UNAUTHORIZED, "用户名为空！");
        }
        String password = new String(token.getPassword());
        if (StringUtils.isEmpty(password)) {
            throw new HwException(HttpStatus.HTTP_UNAUTHORIZED, "密码为空！");
        }
        // 进行用户名密码校验
        if (true) {
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setUserId(token.getUsername());
            loginInfo.setToken(UUID.randomUUID().toString());
            //封装返回数据
            Map<String,Object> map = new HashMap<>();
            loginInfo.setMap(map);

            //认证成功之后返回基本信息的封装
            return new SimpleAuthenticationInfo(loginInfo,token.getCredentials(), getName());

        } else {
            throw new HwException(HttpStatus.HTTP_UNAUTHORIZED, "校验未通过！");
        }
    }
}
