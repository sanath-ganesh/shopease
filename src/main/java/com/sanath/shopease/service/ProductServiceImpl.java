package com.sanath.shopease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanath.shopease.dao.ProductDao;
import com.sanath.shopease.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public void save(Product product) {
		productDao.save(product);

	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public Product getProductById(Long productId) {
		return productDao.getProductById(productId);
	}

}
