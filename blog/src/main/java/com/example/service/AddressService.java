package com.example.service;

import com.example.pojo.dto.AddressDTO;

import java.util.List;

/**
 * @Description TODO
 * @Author maxiaowei
 * @Date 2019/8/23 9:12
 * @Version 1.0
 **/
public interface AddressService {

    void getAddressInfo();

    List<AddressDTO> getAllAddress(String province);
}
