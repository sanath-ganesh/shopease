package com.sanath.shopease.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.sanath.shopease.model.Cart;
import com.sanath.shopease.model.CartItem;
import com.sanath.shopease.model.OrderItem;
import com.sanath.shopease.model.Orders;
import com.sanath.shopease.model.Product;
import com.sanath.shopease.model.User;

public class HibernateUtil {

	public static SessionFactory buildSessionFactory() {
		Map<String, Object> settings = new HashMap<>();
		settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
		settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/shopease");
		settings.put("hibernate.connection.username", "root");
		settings.put("hibernate.connection.password", "admin");

		settings.put("hibernate.hbm2ddl.auto", "update");
		settings.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		settings.put("hibernate.show_sql", "true");
		settings.put("hibernate.format-sql", "true");

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();

		MetadataSources metadataSources = new MetadataSources(serviceRegistry);
		metadataSources.addPackage("com.sanath.shopease.model");
		metadataSources.addAnnotatedClasses(Product.class, Cart.class, CartItem.class, Orders.class, OrderItem.class,
				User.class);

		Metadata metadata = metadataSources.buildMetadata();


		return metadata.getSessionFactoryBuilder().build();
	}

}
