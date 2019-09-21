package com.martin.service.impl;

import com.martin.pojo.dto.AddressDTO;
import com.martin.mapper.AddressMapper;
import com.martin.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * <br>
 * created date 2019/8/23 9:12
 *
 * @author maxiaowei
 */

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private static final String KEY = "ba34130c86543d3578ea7c86694007cf";

    private static String url = "https://restapi.amap.com/v3/geocode/geo?key=#&address=+";

    private static String url1 = "https://restapi.amap.com/v3/geocode/regeo?key=#&location=+";

    @Autowired
    private AddressMapper userMapper;

    @Override
    public void getAddressInfo() {
        List<AddressDTO> address = userMapper.getAllAddress();
        RestTemplate restTemplate = new RestTemplate();
        for (AddressDTO dto : address) {
            String add = dto.getProvince() + dto.getCity();
            //地理编码 API 服务地址
            String getUrl = url.replace("#", KEY).replace("+", add);
            Map result = restTemplate.getForObject(getUrl, Map.class);
            if ("1".equals(result.get("status").toString())) {
                List geocodes = (List) result.get("geocodes");
                Map geocode = (Map) geocodes.get(0);
                String location = geocode.get("location").toString();
                getUrl = url1.replace("#",KEY).replace("+",location);
                result = restTemplate.getForObject(getUrl, Map.class);
                if ("1".equals(result.get("status").toString())) {
                    Map regeocode = (Map) result.get("regeocode");
                    Map addressComponent = (Map) regeocode.get("addressComponent");
                    String formatted_address = regeocode.get("formatted_address").toString();
                    String district = addressComponent.get("district").toString();
                    dto.setLocation(location);
                    dto.setArea(district);
                    dto.setAddress(formatted_address);
                    userMapper.updateAddress(dto);
                    log.info("{}信息添加完成！", dto.getProvince() + dto.getCity());
                }
            }
        }

    }

    @Override
    public List<AddressDTO> getAllAddress(String province) {
        List<AddressDTO> dtos = userMapper.getInfosByProvince(province);
        return dtos;
    }
}
