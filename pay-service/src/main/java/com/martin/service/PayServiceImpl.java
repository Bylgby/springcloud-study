package com.martin.service;

import com.martin.api.PayApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付实现类
 * <br>
 * created date 2019/9/19 11:53
 *
 * @author maxiaowei
 */
@RestController
@Slf4j
public class PayServiceImpl implements PayApi {


    @Override
    public String pay(@PathVariable("name") String name) {
        log.info("支付服务=============== name[{}]", name);
        return null;
    }
}
