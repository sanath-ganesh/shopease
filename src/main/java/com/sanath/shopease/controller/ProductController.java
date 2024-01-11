package com.sanath.shopease.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sanath.shopease.model.Product;
import com.sanath.shopease.service.OrderService;
import com.sanath.shopease.service.ProductService;
import com.sanath.shopease.service.UserService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	@Autowired
	OrderService orderService;

	@PostMapping("/saveProduct")
	public String listProducts(@RequestParam("photo") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("description") String desc, @RequestParam("price") Float price) throws IOException {
		Product product = new Product();
		product.setName(name);
		product.setDescription(desc);
		product.setPrice(price);
		if (file != null && !file.isEmpty()) {
			product.setPhoto(file.getBytes());
		}

		productService.save(product);

		return "product-add-successful";
	}

	@GetMapping("")
	public String listProducts(Model model, Authentication authentication) {

		List<Product> products = productService.getAllProducts();
		for (Product product : products) {
			product.setBase64Photo(Base64.getEncoder().encodeToString(product.getPhoto()));
		}
		if (userService.getUserByUsername(authentication.getName()).getCart() != null) {
			model.addAttribute("cartId", userService.getUserByUsername(authentication.getName()).getCart().getId());
		}
		model.addAttribute("products", products);
		return "productList";
	}

	@GetMapping("/{productId}")
	public String getProductById(@PathVariable Long productId, Model model, Authentication authentication) {
		Product product = productService.getProductById(productId);
		product.setBase64Photo(Base64.getEncoder().encodeToString(product.getPhoto()));
		if (userService.getUserByUsername(authentication.getName()).getCart() != null) {
			model.addAttribute("cartId", userService.getUserByUsername(authentication.getName()).getCart().getId());
		}
		model.addAttribute("product", product);
		model.addAttribute("userId", userService.getUserByUsername(authentication.getName()).getId());
		return "product-details";
	}

	@GetMapping("/addproduct")
	public String showAddProductPage() {
		return "addproduct";
	}

}
