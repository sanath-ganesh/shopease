package com.sanath.shopease.dao;

import java.util.List;

import com.sanath.shopease.model.Cart;
import com.sanath.shopease.model.Orders;
import com.sanath.shopease.model.User;

public interface OrderDao {

	Orders createOrder(User user, Cart cart);

	List<Orders> getOrdersByUser(User user);

	Orders getOrderById(Long orderId);

}
