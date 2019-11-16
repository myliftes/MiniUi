package com.yh.user.serviceImpl;

import org.springframework.stereotype.Service;

import com.yh.user.entity.User;
import com.yh.user.mapper.UserMapper;
import com.yh.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    private UserMapper userMapper;
	@Override
	public User fndByUserName(String userName) {
		
		return this.userMapper.fndByUserName(userName);
	}

}
