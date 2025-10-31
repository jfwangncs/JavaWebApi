package jfwang.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jfwang.api.constant.ErrorCode;
import jfwang.api.constant.ResponseCode;
import jfwang.api.model.BaseResponse;

public class CustomBaseController {
    public <T> ResponseEntity<BaseResponse<T>> Ok() {
        BaseResponse<T> resp = new BaseResponse<>();
        return ResponseEntity.ok(resp);
    }

    public <T> ResponseEntity<BaseResponse<T>> Ok(T data) {
        BaseResponse<T> resp = new BaseResponse<>();
        resp.setData(data);
        return ResponseEntity.ok(resp);
    }

    public static <T> ResponseEntity<BaseResponse<T>> Ok(String msg, T data) {
        BaseResponse<T> resp = new BaseResponse<>();
        resp.setMsg(msg);
        resp.setData(data);
        return ResponseEntity.ok(resp);
    }

    public static <T> ResponseEntity<BaseResponse<T>> Failed(ErrorCode errorCode) {
        BaseResponse<T> resp = new BaseResponse<>();
        resp.setCode(ResponseCode.FAILED.GetCode());
        resp.setMsg(errorCode.GetMsg());
        resp.setErrorCode(errorCode.GetCode());
        return ResponseEntity.ok(resp);
    }

    public static <T> ResponseEntity<BaseResponse<T>> Failed(ErrorCode errorCode, T data) {
        BaseResponse<T> resp = new BaseResponse<>();
        resp.setCode(ResponseCode.FAILED.GetCode());
        resp.setMsg(errorCode.GetMsg());
        resp.setErrorCode(errorCode.GetCode());
        resp.setData(data);
        return ResponseEntity.ok(resp);
    }

    public static <T> ResponseEntity<BaseResponse<T>> Bad(ErrorCode errorCode) {
        BaseResponse<T> resp = new BaseResponse<>();
        resp.setCode(ResponseCode.FAILED.GetCode());
        resp.setMsg(errorCode.GetMsg());
        resp.setErrorCode(errorCode.GetCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

    public static <T> ResponseEntity<BaseResponse<T>> Bad(ErrorCode errorCode, T data) {
        BaseResponse<T> resp = new BaseResponse<>();
        resp.setCode(ResponseCode.FAILED.GetCode());
        resp.setMsg(errorCode.GetMsg());
        resp.setErrorCode(errorCode.GetCode());
        resp.setData(data);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

    public static <T> ResponseEntity<BaseResponse<T>> Result(ResponseCode code, ErrorCode errorCode, T data,
            HttpStatus HttpStatus) {
        if (code == null || errorCode == null) {
            throw new IllegalArgumentException("code and errorCode must not be null");
        }
        BaseResponse<T> resp = new BaseResponse<>();
        resp.setCode(code.GetCode());
        resp.setMsg(errorCode.GetMsg());
        resp.setErrorCode(errorCode.GetCode());
        resp.setData(data);
        return ResponseEntity.status(HttpStatus).body(resp);
    }
}
