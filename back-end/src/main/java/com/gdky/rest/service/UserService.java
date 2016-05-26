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

	public User getUser(String userName){
		List<User> ls =  userDao.getUser(userName);
		if (ls.size()!=1){
			return null;
		}
		return ls.get(0);
	}

	public List<Role> getRolesByUser(String userName) {
		return userDao.getRolesByUser(userName);
	}
	

}
