package jfwang.api.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

import jfwang.api.constant.ErrorCode;
import jfwang.api.constant.ResponseCode;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    private String Code = ResponseCode.SUCCESS.GetCode();
    private String Msg = ResponseCode.SUCCESS.GetMsg();
    private T Data;
    private String ErrorCode;

    public static <T> ResponseEntity<BaseResponse<T>> Ok() {
        BaseResponse<T> resp = new BaseResponse<>();
        return ResponseEntity.ok(resp);
    }

    public static <T> ResponseEntity<BaseResponse<T>> Ok(String msg) {
        BaseResponse<T> resp = new BaseResponse<>();
        resp.setMsg(msg);
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
            HttpStatusCode HttpStatus) {
        BaseResponse<T> resp = new BaseResponse<>();
        resp.setCode(code.GetCode());
        resp.setMsg(errorCode.GetMsg());
        resp.setErrorCode(errorCode.GetCode());
        resp.setData(data);
        return ResponseEntity.status(HttpStatus).body(resp);
    }
}