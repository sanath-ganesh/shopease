package com.sanath.shopease.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.sanath.shopease.model.User;
import com.sanath.shopease.util.HibernateUtil;

@Component
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

	@Override
	public boolean isUserExists(String userName) {
		try (Session session = sessionFactory.openSession()) {
			Query query = session.createQuery("from User where userName = :userName");
			query.setParameter("userName", userName);
			User user = (User) query.uniqueResult();
			return user != null;
		} catch (Exception e) {
			e.printStackTrace();
			return (Boolean) null;
		}
	}

	@Override
	public void registerNewUser(User user) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			session.persist(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(Long user_id) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			return session.get(User.class, user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		try (Session session = sessionFactory.openSession()) {
			List<User> userList = session.createQuery("from User").list();
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public User getUserByUsername(String userName) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			Query query = session.createQuery("from User where userName = :userName");
			query.setParameter("userName", userName);
			return (User) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
