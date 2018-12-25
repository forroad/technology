package com.ycjw.technology.config;

import com.ycjw.technology.exception.ExceptionZyc;
import com.ycjw.technology.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@RestController
@RestControllerAdvice
public class ErrorsConfig implements ErrorController {
    private final Logger log = LoggerFactory.getLogger(ErrorsConfig.class);

    @ExceptionHandler(ExceptionZyc.class)
    public Response onExceptionZyc(ExceptionZyc exceptionZyc){
        log.error("message = {}",exceptionZyc.getResponse().getMessage());
        return exceptionZyc.getResponse();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response onException(Exception e){
        e.printStackTrace();
        return new Response("服务器错误",null);
    }

    @Override
    public String getErrorPath() {
        return "/errors";
    }

    @RequestMapping("/errors")
    public void onNotFound(HttpServletResponse response) throws IOException {
        log.warn("404");
        response.sendRedirect("/swagger-ui.html#");
    }
}