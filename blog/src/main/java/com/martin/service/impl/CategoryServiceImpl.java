package com.martin.service.impl;

import com.martin.pojo.entity.Category;
import com.martin.mapper.CategoryMapper;
import com.martin.service.CategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
