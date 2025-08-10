package jfwang.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jfwang.api.entity.User;

/**
 * 用户服务接口
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询用户
     *
     * @param page     分页参数
     * @param username 用户名（可选）
     * @return 用户分页数据
     */
    Page<User> getUserPage(Page<User> page);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User getUserByUsername(String username);
}
