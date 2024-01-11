package com.sanath.shopease.service;

import java.util.List;

import com.sanath.shopease.model.Product;

public interface ProductService {

	public List<Product> getAllProducts();

	public void save(Product product);

	public Product getProductById(Long productId);

}
