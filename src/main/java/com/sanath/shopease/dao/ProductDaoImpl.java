package com.sanath.shopease.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.sanath.shopease.model.Product;
import com.sanath.shopease.util.HibernateUtil;

@Component
public class ProductDaoImpl implements ProductDao {
	private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

	@Override
	public List<Product> getAllProducts() {
		try (Session session = sessionFactory.openSession()) {
			List<Product> productList = session.createQuery("from Product").list();
			return productList;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public void save(Product product) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			session.persist(product);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Product getProductById(Long id) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			Product product = session.get(Product.class, id);
			return product;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
