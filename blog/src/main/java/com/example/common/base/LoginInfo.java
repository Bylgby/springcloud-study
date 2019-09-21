package com.example.common.base;

import lombok.Data;

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
public class LoginInfo {

    private String token;

    private String userId;

    private Map<String,Object> map = new HashMap<>();
}
