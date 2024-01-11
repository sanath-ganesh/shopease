package com.sanath.shopease.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sanath.shopease.model.Orders;
import com.sanath.shopease.model.User;
import com.sanath.shopease.service.OrderService;
import com.sanath.shopease.service.UserService;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	UserService userService;

	@GetMapping
	public String getOrdersForUser(Model model, Authentication authentication) {

		User user = userService.getUserByUsername(authentication.getName());
		List<Orders> orders = orderService.getOrdersByUser(user);
		model.addAttribute("orders", orders);
		return "view-orders";
	}

}
