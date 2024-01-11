package com.sanath.shopease.service;

import java.util.List;

import com.sanath.shopease.model.Cart;
import com.sanath.shopease.model.Orders;
import com.sanath.shopease.model.User;

public interface OrderService {

	public Orders createOrder(User user, Cart cart);

	public List<Orders> getOrdersByUser(User user);

	public Orders getOrderById(Long orderId);

}
