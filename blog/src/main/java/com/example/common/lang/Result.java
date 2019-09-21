package com.example.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description         结果封装
 * @Author maxiaowei
 * @create 2019/4/22 15:18
 * @Version 1.0
 **/
@Data
public class Result<T> implements Serializable {

    private String code;
    private String msg;
    private T data;

    public static <T> Result<T> succ(T data) {
        Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMsg("操作成功");

        return m;
    }

    public static Result succ() {
        Result m = new Result();
        m.setCode("0");
        m.setData(null);
        m.setMsg("操作成功");

        return m;
    }

    public static <T> Result<T> succ(String mess, T data) {
        Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMsg(mess);

        return m;
    }

    public static Result fail(String mess) {
        Result m = new Result();
        m.setCode("-1");
        m.setData(null);
        m.setMsg(mess);

        return m;
    }

    public static <T> Result<T> fail(String mess, T data) {
        Result m = new Result();
        m.setCode("-1");
        m.setData(data);
        m.setMsg(mess);
        return m;
    }
}
