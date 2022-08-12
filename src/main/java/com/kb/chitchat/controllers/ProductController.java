package com.kb.chitchat.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kb.chitchat.models.Category;
import com.kb.chitchat.models.Product;
import com.kb.chitchat.models.User;
import com.kb.chitchat.services.CategoryService;
import com.kb.chitchat.services.ProductService;
import com.kb.chitchat.services.UserService;


@Controller
public class ProductController {
	@Autowired 
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired 
	private UserService userService;
	
	   
    // ! SHOW  =================================================================
	
	
	@GetMapping("/home")
    public String home(HttpSession session, Model model) {
    	Long userId = (Long)session.getAttribute("userId");
    	
    	// put user back if not logged in
    	if (userId == null) {
    		return "redirect:/";
    	} 
    	
    	User user = userService.findUser(userId);
    	model.addAttribute("user", user);
    	
    	 // change here
    	List<Product> products = productService.allProducts();
    	model.addAttribute("products", products);
    	
    	List<Category> categorys = categoryService.allCategorys();
    	model.addAttribute("categorys", categorys);
    	
    	return "dashboard.jsp";
    }
	
    // ! show 1 product page
    @GetMapping("/products/{id}/show")
    public String showProduct(@PathVariable("id") Long id, Model model, HttpSession session) {
    	model.addAttribute("product", productService.findProduct(id));
    	model.addAttribute("userId", (Long)session.getAttribute("userId"));
    	
    	// errors
    	
    	model.addAttribute("categorys", categoryService.allCategoriesNotOfProduct(productService.findProduct(id)));
//    	model.addAttribute("categorys", categoryService.allCategoriesOfProduct(productService.findProduct(id)));
    	return "showProduct.jsp";
    	
    }
   
	
    
	 // ! Create  =================================================================
    // ! new Product
    @GetMapping("/products/add")
    public String addProduct(@ModelAttribute("product") Product product, Model model,
    		HttpSession session) {
    	model.addAttribute("userId", (Long)session.getAttribute("userId"));
    	
    	
        return "newProduct.jsp";
    }
    
    // ! POST products
    @PostMapping("/products/add")
    public String createProduct(@Valid @ModelAttribute("product") Product product,
            BindingResult result) {

        if (result.hasErrors()) {
            return "newProduct.jsp";
        }
       
        productService.saveProduct(product);
        
        return "redirect:/home";
    }
 
    // ! Add connection
    @PostMapping("/products/addConnect")
    public String addConnect(@RequestParam("categoryId") Long categoryId,
    											@RequestParam("productId") Long productId) {
    	
    	categoryService.addConnect(categoryId, productId);
    	return "redirect:/products/ " + productId.toString() + "/show";
    	
    	
    }
    
    // ! Edit =================================================================
    // ! Edit product page
    @GetMapping("/products/{id}/edit")
    public String editProduct(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("product", productService.findProduct(id));
    	return "editProduct.jsp";

    }
    
    
    // Edit put request
    @RequestMapping(value="/products/{id}/edit", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return  "editProduct.jsp";
        } else {
            productService.saveProduct(product);
            return "redirect:/home";
        }
        
        
        
    }
    
    
    // Delete Route
    
    
    

}
