package com.martin.service;

import com.martin.pojo.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Martin-yuyy
 * @since 2019-04-22
 */
public interface PostService extends IService<Post> {

    /**
     * @Description     初始化本周热议
     * @Author maxiaowei
     * @Date 2019/4/23 15:20
     * @param:
     * @return void
     **/
    void initIndexWeekRank();
}
