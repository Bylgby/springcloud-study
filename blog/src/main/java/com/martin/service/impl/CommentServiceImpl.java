package com.martin.service.impl;

import com.martin.pojo.entity.Comment;
import com.martin.mapper.CommentMapper;
import com.martin.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
