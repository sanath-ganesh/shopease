package com.sanath.shopease.dao;

import com.sanath.shopease.model.Cart;
import com.sanath.shopease.model.Product;

public interface CartDao {

	Cart getCartById(Long cartId);

	void addToCart(Cart cart, Product product, int quantity);

	void removeFromCart(Long itemId);

	void clearCart(Cart cart);

	void createCart(Cart cart);

}
