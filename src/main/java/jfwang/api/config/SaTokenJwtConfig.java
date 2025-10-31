package jfwang.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.dev33.satoken.jwt.StpLogicJwtForStateless;
import cn.dev33.satoken.stp.StpLogic;

/**
 * Sa-Token JWT配置类
 */
@Configuration
public class SaTokenJwtConfig {

    /**
     * Sa-Token 整合 JWT (Stateless无状态模式)
     */
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForStateless();
    }
}