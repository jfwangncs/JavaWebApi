package jfwang.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Hidden;
import jfwang.api.constant.ErrorCode;
import jfwang.api.constant.ResponseCode;
import jfwang.api.model.BaseResponse;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger _logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public <T> ResponseEntity<BaseResponse<T>> handleException(Exception e) {
        _logger.error("服务器端异常", e);
        BaseResponse<T> resp = new BaseResponse<>();
        resp.setCode(ResponseCode.ERROR.GetCode());
        resp.setMsg(ErrorCode.SYSTEM_ERROR.GetMsg());
        resp.setErrorCode(ErrorCode.SYSTEM_ERROR.GetCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

}
