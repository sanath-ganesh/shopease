package com.sanath.shopease.controller;

import java.util.Base64;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sanath.shopease.model.Cart;
import com.sanath.shopease.model.CartItem;
import com.sanath.shopease.model.Product;
import com.sanath.shopease.model.User;
import com.sanath.shopease.service.CartService;
import com.sanath.shopease.service.ProductService;
import com.sanath.shopease.service.UserService;
import com.sanath.shopease.util.HibernateUtil;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	@GetMapping("/{cartId}")
	public String getCart(@PathVariable Long cartId, Model model) {
		Cart cart = cartService.getCartById(cartId);
		for (CartItem item : cart.getCartItems()) {
			Product product = item.getProduct();
			product.setBase64Photo(Base64.getEncoder().encodeToString(product.getPhoto()));
		}
		double cartTotal = cart.getCartItems().stream()
				.mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice()).sum();
		model.addAttribute("cart", cart);
		model.addAttribute("cartTotal", cartTotal);
		return "cartpage";
	}

	private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

	@PostMapping("/{user_id}/add")
	public String addToCart(@PathVariable Long user_id, @RequestParam Long productId, @RequestParam int quantity) {
		User user = userService.getUser(user_id);
		Product product = productService.getProductById(productId);
		Cart cart = user.getCart();
		if (cart == null) {
			cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
			cartService.createCart(cart);
		}

		cartService.addToCart(cart, product, quantity);
		return "redirect:/cart/" + cart.getId();
	}

	@PostMapping("/item/{itemId}")
	public String removeFromCart(@PathVariable Long itemId, Model model, Authentication authentication) {
		cartService.removeFromCart(itemId);
		Cart cart = cartService.getCartById(userService.getUserByUsername(authentication.getName()).getCart().getId());
		for (CartItem item : cart.getCartItems()) {
			Product product = item.getProduct();
			product.setBase64Photo(Base64.getEncoder().encodeToString(product.getPhoto()));
		}
		model.addAttribute("cart", cart);
		return "redirect:/cart/" + cart.getId();
	}

}
