package com.example.service.impl;

import com.example.pojo.entity.UserMessage;
import com.example.mapper.UserMessageMapper;
import com.example.service.UserMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Martin-yuyy
 * @since 2019-04-22
 */
@Service
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage> implements UserMessageService {

}
