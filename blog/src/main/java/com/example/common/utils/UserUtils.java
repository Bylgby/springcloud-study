package com.example.common.utils;

import com.example.common.base.LoginInfo;
import org.apache.shiro.SecurityUtils;

/**
 * 获取当前登录用户
 * <br>
 * created date 2019/8/30 11:21
 *
 * @author maxiaowei
 */

public class UserUtils {


    /**
     * @Description     获取当前用户信息
     * @Date  2019/8/30 11:22
     * @Author maxiaowei
     **/
    public static LoginInfo getUserInfo(){
        Object principal = SecurityUtils.getSubject().getPrincipal();

        return (LoginInfo) principal;
    }
}
