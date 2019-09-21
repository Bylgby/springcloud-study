package com.martin.mapper;

import com.martin.pojo.dto.AddressDTO;

import java.util.List;

/**
 * @Description TODO
 * @Author maxiaowei
 * @Date 2019/8/23 9:51
 * @Version 1.0
 **/
public interface AddressMapper {

    List<AddressDTO> getAllAddress();

    void updateAddress(AddressDTO dto);

    List<AddressDTO> getInfosByProvince(String province);
}
