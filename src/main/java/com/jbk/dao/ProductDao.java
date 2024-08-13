package com.jbk.dao;

import java.util.List;

import com.jbk.model.Product;

public interface ProductDao {
	
	public int addProduct(Product product);
	public Product getProductById(long productId);
	public List<Product> getAllProducts();
	public int deleteProduct(long productId);
	public int updateProduct(Product product);

}
