package com.sanath.shopease.dao;

import java.util.List;

import com.sanath.shopease.model.User;

public interface UserDao {

	boolean isUserExists(String userName);

	void registerNewUser(User user);

	User getUser(Long user_id);

	List<User> getAllUsers();

	User getUserByUsername(String userName);

}
