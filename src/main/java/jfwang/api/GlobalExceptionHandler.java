package jfwang.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Hidden;
import jfwang.api.constant.ErrorCode;
import jfwang.api.model.BaseResponse;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger _logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public <T> ResponseEntity<BaseResponse<T>> handleException(Exception e) {
        _logger.error("服务器端异常", e);
        return BaseResponse.Failed(ErrorCode.SYSTEM_ERROR);
    }

}
