package com.martin.pojo.dto;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.base.Converter;
import com.martin.common.base.BaseEntity;
import com.martin.pojo.entity.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Martin-yuyy
 * @since 2019-04-22
 */
@Data
@Accessors(chain = true)
public class CategoryDTO{

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String name;

    /**
     * 内容描述
     */
    private String content;

    private String summary;

    /**
     * 图标
     */
    private String icon;

    /**
     * 该分类的内容数量
     */
    private Integer postCount;

    /**
     * 排序编码
     */
    private Integer orderNum;

    /**
     * 父级分类的ID
     */
    private Long parentId;

    /**
     * SEO关键字
     */
    private String metaKeywords;

    /**
     * SEO描述内容
     */
    private String metaDescription;

    public Category convertToCategory(CategoryDTO categoryDTO){
        CategoryDTOConver categoryDTOConver = new CategoryDTOConver();
        Category category = categoryDTOConver.doForward(categoryDTO);
        return category;
    }

    private static class CategoryDTOConver extends Converter<CategoryDTO, Category>{

        @Override
        protected Category doForward(CategoryDTO categoryDTO) {
            Category category = new Category();
            BeanUtil.copyProperties(categoryDTO, category);
            return category;
        }

        @Override
        protected CategoryDTO doBackward(Category category) {
            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtil.copyProperties(category, categoryDTO);
            return categoryDTO;
        }
    }
}
