package jfwang.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jfwang.api.entity.User;
import jfwang.api.mapper.UserMapper;
import jfwang.api.model.BaseResponse;
import lombok.RequiredArgsConstructor;

@Tag(name = "认证管理", description = "用户登录、登出相关接口")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController extends CustomBaseController {

    @Autowired
    private final UserMapper userMapper;

    @Operation(summary = "用户登录", description = "通过用户名和密码登录系统")
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<SaTokenInfo>> login(
            @Parameter(description = "用户名", example = "admin") @RequestParam String userName,
            @Parameter(description = "密码", example = "123456") @RequestParam String password) {

        StpUtil.login(10001, new SaLoginModel()
                .setExtra("name", "zhangsan")
                .setExtra("age", 18)
                .setExtra("role", "超级管理员"));

        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        return Ok(tokenInfo);
    }

    @Operation(summary = "获取当前用户信息", description = "获取当前登录用户的详细信息")
    @GetMapping("/userInfo")
    @SaCheckLogin
    public ResponseEntity<Map<String, Object>> getUserInfo() {
        // 获取当前登录用户的 id
        long loginId = StpUtil.getLoginIdAsLong();

        // 查询用户信息
        User user = userMapper.selectById(loginId);

        Map<String, Object> result = new HashMap<>();
        if (user != null) {
            result.put("code", 200);
            result.put("message", "获取成功");
            result.put("data", user);
        } else {
            result.put("code", 500);
            result.put("message", "用户不存在");
        }

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "检查登录状态", description = "检查当前用户是否已登录")
    @GetMapping("/isLogin")
    public ResponseEntity<Map<String, Object>> isLogin() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);

        boolean isLoginStatus = StpUtil.isLogin();
        Map<String, Object> data = new HashMap<>();
        data.put("isLogin", isLoginStatus);

        if (isLoginStatus) {
            data.put("loginId", StpUtil.getLoginId());
            // 在JWT Stateless模式下，tokenInfo可能不可用，所以只返回基本信息
            data.put("tokenValue", StpUtil.getTokenValue());
        } else {
            data.put("loginId", null);
            data.put("tokenValue", null);
        }

        result.put("data", data);
        return ResponseEntity.ok(result);
    }
}