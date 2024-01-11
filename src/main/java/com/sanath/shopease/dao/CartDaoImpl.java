package com.sanath.shopease.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.sanath.shopease.model.Cart;
import com.sanath.shopease.model.CartItem;
import com.sanath.shopease.model.Product;
import com.sanath.shopease.util.HibernateUtil;

@Component
public class CartDaoImpl implements CartDao {

	private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

	@Override
	public Cart getCartById(Long cartId) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			Cart cart = session.get(Cart.class, cartId);
			return cart;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addToCart(Cart cart, Product product, int quantity) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
			cartItem.setCart(cart);
			session.persist(cartItem);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeFromCart(Long itemId) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			CartItem cartItem = session.get(CartItem.class, itemId);
			session.remove(cartItem);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void clearCart(Cart cart) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			cart.getCartItems().clear();
			session.merge(cart);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void createCart(Cart cart) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			session.persist(cart);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
