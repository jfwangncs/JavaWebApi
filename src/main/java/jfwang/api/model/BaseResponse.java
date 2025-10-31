package jfwang.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import jfwang.api.constant.ResponseCode;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    private String Code = ResponseCode.SUCCESS.GetCode();
    private String Msg = ResponseCode.SUCCESS.GetMsg();
    private T Data;
    private String ErrorCode;
}