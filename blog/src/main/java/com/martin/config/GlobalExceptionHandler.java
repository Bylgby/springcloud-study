package com.martin.config;

import com.martin.common.exception.HwException;
import com.martin.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description         全局自定义异常处理
 * @Author maxiaowei
 * @create 2019/4/22 15:08
 * @Version 1.0
 **/
@Slf4j
@ControllerAdvice  //表示定义全局控制器异常处理
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)  //表示针对性异常处理，可对每种异常针对性处理。
    public ModelAndView defaultErrorHandler(HttpServletRequest request,Exception e){
        log.error("------------------------------->捕获到全局异常",e);

        if (e instanceof Exception) {
            //针对处理
        }

        ModelAndView model = new ModelAndView();
        model.addObject("exception",e);
        model.addObject("message",e.getMessage());
        model.addObject("url",request.getRequestURL());
        model.setViewName("error");
        return model;
    }

    @ExceptionHandler(value = HwException.class)  //表示针对性异常处理，可对每种异常针对性处理。
    @ResponseBody
    public Result jsonErrorHandler(HttpServletRequest request,HwException e){
        return Result.fail(e.getMessage(), "some error data");
    }
}
