package com.example.controller;

import com.example.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description         主页
 * @Author maxiaowei
 * @create 2019/4/22 14:50
 * @Version 1.0
 **/
@Controller
public class IndexController extends BaseController {

    @RequestMapping({"","/","/index"})
    public String index(){
        return "index";
    }
}
