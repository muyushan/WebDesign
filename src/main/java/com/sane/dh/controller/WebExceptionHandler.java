package com.sane.dh.controller;

import com.sane.dh.model.common.ReturnValue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WebExceptionHandler {

    private Log logger= LogFactory.getLog(WebExceptionHandler.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ReturnValue handlerBizException(Exception e){
        e.printStackTrace();
        ReturnValue returnValue=new ReturnValue();
        returnValue.setCode("500");
        returnValue.setMessage(e.getMessage());
        return  returnValue;


    }
}
