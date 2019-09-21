package com.example.common.base;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录用户信息
 * <br>
 * created date 2019/8/30 10:43
 *
 * @author maxiaowei
 */
@Data
public class SecurityToken extends UsernamePasswordToken {

    private String channelType;

    public SecurityToken(String channelType) {
        this.channelType = channelType;
    }
}
