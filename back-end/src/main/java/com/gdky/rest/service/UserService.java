package com.gdky.rest.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdky.rest.dao.UserDao;
import com.gdky.rest.entity.Role;
import com.gdky.rest.entity.User;

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
