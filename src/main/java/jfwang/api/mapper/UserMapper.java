package jfwang.api.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jfwang.api.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}