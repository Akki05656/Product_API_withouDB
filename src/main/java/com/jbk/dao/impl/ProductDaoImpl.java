package com.jbk.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.jbk.dao.ProductDao;
import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.exception.SomethingWentWrongException;
import com.jbk.model.Product;

@Component
public class ProductDaoImpl implements ProductDao {

	List<Product> list = new ArrayList<>();

	public ProductDaoImpl() {
		list.add(new Product(1, "xyz", 10, 100, "2023-01-12", "2025-02-04"));
		list.add(new Product(2, "abc", 10, 100, "2023-01-12", "2025-02-04"));
		list.add(new Product(3, "pqr", 10, 100, "2023-01-12", "2025-02-04"));
	}

	@Override
	public int addProduct(Product product) {

		try {

			for (Product listProduct : list) {
				if (listProduct.getProductName().equalsIgnoreCase(product.getProductName())) {

					throw new ResourceAlreadyExistException(
							"Product Already Exists . Product Name = " + product.getProductName());
				}
			}

			list.add(product);

			return 1;
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something Went Wrong While Add Product");
		}

	}

	@Override
	public Product getProductById(long productId) {

		try {
			for (Product product : list) {

				if (product.getProductId() == productId) {
					return product;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something Went Wrong While Retrive Product");
		}

		throw new ResourceNotExistsException("Product Not Found With Id >> " + productId);
	}

	@Override
	public List<Product> getAllProducts() {

		return list;
	}

	@Override
	public int deleteProduct(long productId) {
		try {
			if (!list.isEmpty()) {

				boolean isDeleted = list.removeIf(product -> product.getProductId() == productId);
				if (isDeleted)
					return 1;

//				for (Product product : list) {
//					if(product.getProductId()==productId){
//						list.remove(product);
//						return 1;
//					}
//					
//				}
			} else {
				throw new ResourceNotExistsException("Product Not Exists to delete, List Is Empty ");
			}

		} catch (ResourceNotExistsException e) {
			throw new ResourceNotExistsException("Product Not Exists to delete, List Is Empty ");
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something Went Wrong While Delete");
		}
		throw new ResourceNotExistsException("Product Not Exists to delete , Id  = " + productId);

	}

	@Override
	public int updateProduct(Product product) {
		try {
			
			
			int status = list.stream()
	                .filter(listProduct -> listProduct.getProductId() == product.getProductId())
	                .peek(listProduct -> list.set(list.indexOf(listProduct), product))
	                .mapToInt(e -> 1)
	                .findFirst()
	                .orElse(0);

	return status;	
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong while update");
		}
	}

}
