package com.example.service;

import com.example.pojo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Martin-yuyy
 * @since 2019-04-22
 */
public interface UserService extends IService<User> {

    void addUsers();
}
