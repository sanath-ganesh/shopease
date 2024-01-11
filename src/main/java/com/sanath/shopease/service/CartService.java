package com.sanath.shopease.service;

import com.sanath.shopease.model.Cart;
import com.sanath.shopease.model.Product;

public interface CartService {

	public Cart getCartById(Long cartId);

	public void addToCart(Cart cart, Product product, int quantity);

	public void removeFromCart(Long itemId);

	public void clearCart(Cart cart);

	public void createCart(Cart cart);

}
