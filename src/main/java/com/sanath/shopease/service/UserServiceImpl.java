package com.sanath.shopease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanath.shopease.dao.UserDao;
import com.sanath.shopease.model.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	@Override
	public boolean isUserExists(String userName) {
		return userDao.isUserExists(userName);
	}

	@Override
	public void registerNewUser(User user) {
		userDao.registerNewUser(user);
	}

	@Override
	public User getUser(Long user_id) {
		return userDao.getUser(user_id);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public User getUserByUsername(String userName) {
		return userDao.getUserByUsername(userName);
	}

}
