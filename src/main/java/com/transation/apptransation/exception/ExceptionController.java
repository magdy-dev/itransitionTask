package com.transation.apptransation.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
@Log4j2
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = Exception.class)
    public String handleException(HttpServletRequest request,Exception exception){

        log.error("Request :"+request.getRequestURI()+"error :"+exception);

        return "error";
    }
}
