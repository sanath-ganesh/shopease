package com.sanath.shopease.service;

import java.util.List;

import com.sanath.shopease.model.User;

public interface UserService {

	public boolean isUserExists(String userName);

	public void registerNewUser(User user);

	public User getUser(Long user_id);

	public List<User> getAllUsers();

	public User getUserByUsername(String userName);

}
