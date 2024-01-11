package com.sanath.shopease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanath.shopease.dao.CartDao;
import com.sanath.shopease.model.Cart;
import com.sanath.shopease.model.Product;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartDao cartDao;

	@Override
	public Cart getCartById(Long cartId) {

		return cartDao.getCartById(cartId);
	}

	@Override
	public void addToCart(Cart cart, Product product, int quantity) {
		cartDao.addToCart(cart, product, quantity);

	}

	@Override
	public void removeFromCart(Long itemId) {
		cartDao.removeFromCart(itemId);

	}

	@Override
	public void clearCart(Cart cart) {
		cartDao.clearCart(cart);

	}

	@Override
	public void createCart(Cart cart) {
		cartDao.createCart(cart);

	}

}
