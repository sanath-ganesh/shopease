package com.sanath.shopease.dao;

import java.util.List;

import com.sanath.shopease.model.Product;

public interface ProductDao {

	List<Product> getAllProducts();

	void save(Product product);

	Product getProductById(Long id);

}
