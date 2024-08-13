package com.jbk.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.dao.ProductDao;
import com.jbk.dao.impl.ProductDaoImpl;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.exception.SomethingWentWrongException;
import com.jbk.model.Product;
import com.jbk.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService {
	
	//ProductDao dao=new ProductDaoImpl();
	
	@Autowired
	private ProductDao dao;

	@Override
	public int addProduct(Product product) {
		
		String productId = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		product.setProductId(Long.parseLong(productId));
		
		System.out.println(productId);
		
		return dao.addProduct(product);
	
	}

	@Override
	public Product getProductById(long productId) {
		
		return dao.getProductById(productId);
	}

	@Override
	public List<Product> getAllProducts() {
		
		return dao.getAllProducts();
	}

	@Override
	public int deleteProduct(long productId) {
		
		return dao.deleteProduct(productId);
		
	}

	@Override
	public int updateProduct(Product product) {
	return dao.updateProduct(product);
		
	}
}
