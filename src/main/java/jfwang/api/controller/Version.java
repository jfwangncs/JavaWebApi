package jfwang.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jfwang.api.constant.ErrorCode;
import jfwang.api.model.BaseResponse;

@RestController
@RequestMapping
public class Version {

    private static final Logger logger = LoggerFactory.getLogger(Version.class);

    @GetMapping("/version")
    public ResponseEntity<BaseResponse<String>> version() {
        logger.info("测试abc123");
        return BaseResponse.Bad(ErrorCode.OUTOFSTOCK);
    }
}
