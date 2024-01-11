package com.sanath.shopease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanath.shopease.dao.OrderDao;
import com.sanath.shopease.model.Cart;
import com.sanath.shopease.model.Orders;
import com.sanath.shopease.model.User;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;

	@Override
	public Orders createOrder(User user, Cart cart) {
		return orderDao.createOrder(user, cart);
	}

	@Override
	public List<Orders> getOrdersByUser(User user) {
		return orderDao.getOrdersByUser(user);
	}

	@Override
	public Orders getOrderById(Long orderId) {
		return orderDao.getOrderById(orderId);
	}

}
