package com.sanath.shopease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sanath.shopease.model.Cart;
import com.sanath.shopease.model.User;
import com.sanath.shopease.service.CartService;
import com.sanath.shopease.service.OrderService;
import com.sanath.shopease.service.UserService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

	@Autowired
	OrderService orderService;

	@Autowired
	CartService cartService;

	@Autowired
	UserService userService;

	@PostMapping("/process")
	public String processCheckout(Authentication authentication) {
		User user = userService.getUserByUsername(authentication.getName());
		Cart cart = user.getCart();
		orderService.createOrder(user, cart);

		cartService.clearCart(user.getCart());

		return "order-placed";
	}

}
