package jfwang.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Version {

    private static final Logger logger = LoggerFactory.getLogger(Version.class);

    @GetMapping("/version")
    public String version() {
        logger.info("测试abc123");
        logger.warn("warning 123 abcsd 1");
        logger.error("错误123");
        return "0.0.1";
    }
}
