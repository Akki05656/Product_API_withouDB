package com.jbk.service;

import java.util.List;

import com.jbk.model.Product;

public interface ProductService {
	
	public int addProduct(Product product);
	public Product getProductById(long productId);

	public List<Product> getAllProducts();
	
	public int deleteProduct(long productId);
	
	public int updateProduct(Product product);
}
