package com.kb.chitchat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kb.chitchat.models.Category;
import com.kb.chitchat.models.Product;
import com.kb.chitchat.repositories.CategoryRepo;
import com.kb.chitchat.repositories.ProductRepo;

@Service
public class CategoryService {
	@Autowired 
	private ProductService productService;  // there might be ciruclar import there.
	
	
	@Autowired 
	private CategoryRepo categoryRepo;
	
	
		
	// read all
	public List<Category> allCategorys(){ 
		return categoryRepo.findAll();
	}
	
	
	public List<Category> allCategoriesOfProduct(Product product) {
		return categoryRepo.findAllByProducts(product);
	}
	
	public List<Category> allCategoriesNotOfProduct(Product product) {
		return categoryRepo.findByProductsNotContains(product);
	}
	
	
	
	// Create and Update
	public Category saveCategory(Category category) {
		return categoryRepo.save(category);
	}
	
	// delete
	public void deleteCategory(Category category) {
		categoryRepo.delete(category);
	}
	
	// read one
	public Category findCategory(Long id) {
		Optional<Category> optionalCategory = categoryRepo.findById(id);
		
		if (optionalCategory.isPresent()) { return optionalCategory.get(); } 
		else { return null;}
	}
	
	// many to many relationship service
	// add cateogry to the product
	public Category addConnect(Long categoryId, Long productId) {
		// retrieve an instance of a category using another method in the service.
	    Category thisCategory = findCategory(categoryId);
	    Product thisProduct = productService.findProduct(productId);
	    // add the product to this category's list of products
	    thisCategory.getProducts().add(thisProduct);
	    
	    // Save thisCategory, since you made changes to its product list.
	    return categoryRepo.save(thisCategory);
		
	}
    
	
	
}
