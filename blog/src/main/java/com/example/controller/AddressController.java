package com.example.controller;

import com.example.common.lang.Result;
import com.example.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>
 * created date 2019/8/23 9:11
 *
 * @author maxiaowei
 */
@RestController
@RequestMapping("/addrss")
@RequiresPermissions("auth2:info")
@Slf4j
public class AddressController {


    @Autowired
    private AddressService addressService;

    @GetMapping("/info")
    public Result getAddressInfo(){
        addressService.getAddressInfo();
        return Result.succ();
    }

    @GetMapping("/getAllAddress")
    public Result getAllAddress(String province){
        return Result.succ(addressService.getAllAddress(province));
    }
}
