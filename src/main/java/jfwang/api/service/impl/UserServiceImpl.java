package jfwang.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jfwang.api.entity.User;
import jfwang.api.mapper.UserMapper;
import jfwang.api.service.UserService;
import org.springframework.stereotype.Service; 

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Page<User> getUserPage(Page<User> page) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>(); 
        return this.page(page, queryWrapper);
    }

    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        return this.getOne(queryWrapper);
    }
}
