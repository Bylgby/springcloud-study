package com.example.controller;


import com.example.common.lang.Result;
import com.example.pojo.entity.User;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.common.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Martin-yuyy
 * @since 2019-04-22
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @GetMapping("/byId")
    public Result getUserById(Long id){
        log.info("根据id获取用户，参数 [{}]",id);
        User user = userService.getById(id);
        return Result.succ(user);
    }

    @PostMapping(value = "/addUsers")
    public Result addUsers(){
        log.info("批量添加用户，参数 [{}]");
        userService.addUsers();
        return Result.succ("");
    }

}
