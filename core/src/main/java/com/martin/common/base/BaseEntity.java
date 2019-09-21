package com.martin.common.base;

import lombok.Data;

import java.util.Date;

/**
 * @Description         基础entity
 * @Author maxiaowei
 * @create 2019/4/22 11:02
 * @Version 1.0
 **/
@Data
public class BaseEntity {

    private Long id;
    private Date created;
    private Date modified;
    private Integer status;
}
