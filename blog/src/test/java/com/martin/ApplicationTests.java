package com.martin;

import com.martin.pojo.entity.User;
import com.martin.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {

        User user = new User();
        user.setUsername("马小伟");
        user.setPassword("741852963");
        user.setEmail("1796@163.com");
        user.setMobile("17635127612");
        user.setPoint(500);
        user.setSign("stay hunger");
        user.setGender("男");
        user.setWechat("17635127612");
        user.setVipLevel("15");
        user.setBirthday(null);
        user.setAvatar("");
        user.setPostCount(1);
        user.setCommentCount(1);
        user.setLasted(null);
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setStatus(0);
        userMapper.insert(user);
        System.out.println(user);
    }

}
