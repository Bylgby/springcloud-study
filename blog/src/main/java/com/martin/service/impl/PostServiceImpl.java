package com.martin.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.martin.common.utils.RedisUtils;
import com.martin.pojo.entity.Post;
import com.martin.mapper.PostMapper;
import com.martin.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Martin-yuyy
 * @since 2019-04-22
 */
@Service
@Slf4j
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {


    @Autowired
    private RedisUtils redisUtil;

    /**
     * @Description 初始化本周热议
     * @Author maxiaowei
     * @Date 2019/4/23 15:20
     * @param:
     **/
    @Override
    public void initIndexWeekRank() {
        List<Post> last7DaysPosts = this.list(new QueryWrapper<Post>().ge("created", DateUtil.offsetDay(new Date(),-7).toJdkDate())
                                    .select("id","title","user_id","comment_count","view_count","created"));
        for (Post post : last7DaysPosts) {
            String key = "day_rank:" + DateUtil.format(post.getCreated(), DatePattern.PURE_DATE_PATTERN);
            //设置有效期
            long between = DateUtil.between(new Date(), post.getCreated(), DateUnit.DAY);
            long expireTime =  (7 - between) * 24 * 60 *60;

            //把文章缓存到set中，评论数量作为排行标准
            redisUtil.zSet(key,post.getId(),post.getCommentCount());
            redisUtil.expire(key,expireTime);

        }
    }
}
