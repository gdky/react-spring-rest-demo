package com.gdky.restfull.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdky.restfull.dao.UserDao;
import com.gdky.restfull.entity.Role;
import com.gdky.restfull.entity.User;

@Service
public class UserService {
	
	@Resource
	private UserDao userDao;

	public List<User> getUser(String userName){
		return userDao.getUser(userName);
	}

	public List<Role> getRolesByUser(String userName) {
		return userDao.getRolesByUser(userName);
	}
	

}
