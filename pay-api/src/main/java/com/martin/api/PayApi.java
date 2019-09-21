package com.martin.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description TODO
 * @Author maxiaowei
 * @Date 2019/9/19 11:42
 * @Version 1.0
 **/
@RequestMapping("/pay")
public interface PayApi {

    @GetMapping("/payByName/{name}")
    String pay(
            @PathVariable("name") String name);
}
