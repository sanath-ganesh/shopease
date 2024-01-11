package com.sanath.shopease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sanath.shopease.config.SpringSecurityAuthentication;
import com.sanath.shopease.model.Cart;
import com.sanath.shopease.model.User;
import com.sanath.shopease.service.CartService;
import com.sanath.shopease.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	CartService cartService;

	@Autowired
	SpringSecurityAuthentication springSecurityAuthentication;

	private static final String ERROR = "error";
	private static final String ERRORPAGE = "error-page";

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user,
			@RequestParam("confirmPassword") String confirmPassword, Model model) {
		if (userService.isUserExists(user.getUserName())) {
			model.addAttribute(ERROR, "username already exists");
			return ERRORPAGE;
		}

		if (!user.getPassword().equals(confirmPassword)) {
			model.addAttribute(ERROR, "Passwords do not match");
			return ERRORPAGE;
		}

		Cart cart = new Cart();
		user.setCart(cart);
		cart.setUser(user);
		userService.registerNewUser(user);
		springSecurityAuthentication.reloadUserDetails(user);
		return "registration-success";
	}

	@GetMapping("/register.htm")
	public String register(Authentication authentication, Model model) {
		if (authentication != null) {
			model.addAttribute(ERROR, "Cannot register a new account while logged in. Please log out first.");
			return ERRORPAGE;
		}
		return "register";
	}

}
