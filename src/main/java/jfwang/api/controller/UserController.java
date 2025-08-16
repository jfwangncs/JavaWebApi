package jfwang.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jfwang.api.entity.User;
import jfwang.api.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户管理控制器
 */
@Tag(name = "用户管理", description = "用户相关接口")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    @Autowired
    private final UserMapper userService;

    @Operation(summary = "获取用户列表", description = "分页查询用户列表")
    @GetMapping
    public ResponseEntity<Page<User>> getUserList(
            @Parameter(description = "页码", example = "1") @RequestParam(value = "current", defaultValue = "1") Integer current,
            @Parameter(description = "每页大小", example = "10") @RequestParam(value = "size", defaultValue = "10") Integer size) {

        Page<User> page = new Page<>(current, size);
        Page<User> result = userService.selectPage(page, null);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "根据ID获取用户", description = "根据用户ID获取用户详情")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @Parameter(description = "用户ID", example = "1") @PathVariable("id") Integer id) {
        User user = userService.selectById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "创建用户", description = "创建新用户")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setCreatedTime(LocalDateTime.now());
        user.setUpdatedTime(LocalDateTime.now());
        user.setState(1);
        userService.insert(user);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "更新用户", description = "根据ID更新用户信息")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @Parameter(description = "用户ID", example = "1") @PathVariable("id") int id,
            @RequestBody User user) {
        User existingUser = userService.selectById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        user.setUpdatedTime(LocalDateTime.now());
        userService.updateById(user);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "删除用户", description = "根据ID删除用户")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "用户ID", example = "1") @PathVariable("id") Long id) {
        User user = userService.selectById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "批量删除用户", description = "根据ID列表批量删除用户")
    @DeleteMapping("/batch")
    public ResponseEntity<Void> batchDeleteUsers(@RequestBody List<Long> ids) {
        userService.deleteByIds(ids);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "根据用户名查询用户", description = "根据用户名查询用户信息")
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(
            @Parameter(description = "用户名", example = "admin") @PathVariable("username") String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", username);
        User user = userService.selectOne(queryWrapper);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
