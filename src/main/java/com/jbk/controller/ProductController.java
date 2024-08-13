package com.jbk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.model.Product;
import com.jbk.service.ProductService;
import com.jbk.service.impl.ProductServiceImpl;

@RestController
public class ProductController {
// ProductService service=new ProductServiceImpl();
	@Autowired
	ProductService service;

	@PostMapping("/add-product")
	public String addProduct(@RequestBody @Valid Product product) {

		int status = service.addProduct(product);

			return "Added Successfully";
		
	}

	@GetMapping("/get-product-by-id/{id}") // {_} placeholder
	public ResponseEntity<Product> getProductById(@PathVariable("id") long productId) {

		Product product = service.getProductById(productId);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@GetMapping("/get-all-products")
	public ResponseEntity<List<Product>> getAllProducts(){
		
		List<Product> productList = service.getAllProducts();
		if(!productList.isEmpty()) {
			return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
		}else {
			throw new ResourceNotExistsException("Product Not Exists, List Is Empty");
		}
				
	}
	
	@DeleteMapping("/delete-product-by-id")
	public ResponseEntity<String> deleteProduct(@RequestParam long productId){
		
		int status = service.deleteProduct(productId);
		//return ResponseEntity.ok("Deleted");
		return new ResponseEntity<String>("Deleted",HttpStatus.MOVED_PERMANENTLY);
		
	}
	
	@PutMapping("/update-product")
	public ResponseEntity<String> updateUpdateProduct(@RequestBody @Valid Product product){
		
		int status = service.updateProduct(product);
		if(status==1) {
			return new ResponseEntity<String>("Updated Successfully",HttpStatus.OK);
			
		}else {
			return new ResponseEntity<String>("Not Found",HttpStatus.OK);
			
		}
	
	}
	
	

}
