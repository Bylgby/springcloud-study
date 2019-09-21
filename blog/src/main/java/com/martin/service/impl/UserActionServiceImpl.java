package com.martin.service.impl;

import com.martin.pojo.entity.UserAction;
import com.martin.mapper.UserActionMapper;
import com.martin.service.UserActionService;
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
public class UserActionServiceImpl extends ServiceImpl<UserActionMapper, UserAction> implements UserActionService {

}
