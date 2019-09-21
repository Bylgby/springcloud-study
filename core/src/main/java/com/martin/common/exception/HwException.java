package com.martin.common.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description     自定义异常
 * @Author maxiaowei
 * @create 2019/4/22 15:05
 * @Version 1.0
 **/
@Slf4j
public class HwException extends RuntimeException{

    private int code;

    public HwException(){

    }

    public HwException(int code){
        this.code = code;
    }

    public HwException(String msg){
        super(msg);
    }

    public HwException(int code,String msg){
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
