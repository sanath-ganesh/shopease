package com.sanath.shopease.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.sanath.shopease.model.Cart;
import com.sanath.shopease.model.CartItem;
import com.sanath.shopease.model.OrderItem;
import com.sanath.shopease.model.Orders;
import com.sanath.shopease.model.User;
import com.sanath.shopease.util.HibernateUtil;

@Component
public class OrderDaoImpl implements OrderDao {
	private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

	@Override
	public Orders createOrder(User user, Cart cart) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			Orders order = new Orders();
			order.setUser(user);
			order.setStatus("Received");

			double totalAmount = 0.0;

			for (CartItem cartItem : cart.getCartItems()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setProduct(cartItem.getProduct());
				orderItem.setQuantity(cartItem.getQuantity());
				orderItem.setSubtotal(cartItem.getProduct().getPrice() * cartItem.getQuantity());
				orderItem.setOrder(order);

				totalAmount += orderItem.getSubtotal();

				session.persist(orderItem);
			}

			order.setTotalAmount(totalAmount);

			session.persist(order);
			transaction.commit();
			return order;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Orders> getOrdersByUser(User user) {
		try (Session session = sessionFactory.openSession()) {
			Query query = session.createQuery("from Orders where user.id = :user_id");
			query.setParameter("user_id", user.getId());
			List<Orders> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Orders getOrderById(Long orderId) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			return session.get(Orders.class, orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
