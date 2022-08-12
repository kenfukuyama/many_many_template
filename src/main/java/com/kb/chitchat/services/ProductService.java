package com.kb.chitchat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kb.chitchat.models.Category;
import com.kb.chitchat.models.Product;
import com.kb.chitchat.repositories.ProductRepo;

@Service
public class ProductService {
	@Autowired 
	private ProductRepo productRepo;
		
	// read all
	public List<Product> allProducts(){ 
		return productRepo.findAll();
	}
	
	// read one
	public List<Product> allProductsOfCategory(Category category) {
		return productRepo.findByCategorys(category);
	}
	
	public List<Product> allProductsNotofCategory(Category category) {
		return productRepo.findByCategorysNotContains(category);
	}
	
	
	// Create and Update
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}
	
	// delete
	public void deleteProduct(Product product) {
		productRepo.delete(product);
	}
	
	// read one
	public Product findProduct(Long id) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		
		if (optionalProduct.isPresent()) { return optionalProduct.get(); } 
		else { return null;}
	}
	
	
	
}
