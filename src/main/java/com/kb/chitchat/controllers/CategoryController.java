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
import com.kb.chitchat.services.CategoryService;
import com.kb.chitchat.services.ProductService;
import com.kb.chitchat.services.UserService;


@Controller
public class CategoryController {
	@Autowired 
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	   
	
    // ! show 1 category page
    @GetMapping("/categorys/{id}/show")
    public String showCategory(@PathVariable("id") Long id, Model model, HttpSession session) {
    	model.addAttribute("category", categoryService.findCategory(id));
    	model.addAttribute("userId", (Long)session.getAttribute("userId"));
    	
    	model.addAttribute("products", productService.allProductsNotofCategory(categoryService.findCategory(id)));
    	return "showCategory.jsp";
    	
    	
    	
    }
    
    
	
    
	 // ! Create  =================================================================
    // ! new Category
    @GetMapping("/categorys/add")
    public String addCategory(@ModelAttribute("category") Category category, Model model,
    		HttpSession session) {
    	model.addAttribute("userId", (Long)session.getAttribute("userId"));
    	
        return "newCategory.jsp";

    }
    
    // ! POST categorys
    @PostMapping("/categorys/add")
    public String createCategory(@Valid @ModelAttribute("category") Category category,
            BindingResult result) {

        if (result.hasErrors()) {
            return "newCategory.jsp";
        }

        categoryService.saveCategory(category);
        return "redirect:/home";
    }
 
    
    // ! Add connection
    @PostMapping("/categorys/addConnect")
    public String addConnect(@RequestParam("categoryId") Long categoryId,
    											@RequestParam("productId") Long productId) {
    	
    	categoryService.addConnect(categoryId, productId);
    	return "redirect:/categorys/ " + categoryId.toString() + "/show";
    	
    	
    }
    
    
    // ! Edit =================================================================
    // ! Edit category page
    @GetMapping("/categorys/{id}/edit")
    public String editCategory(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("category", categoryService.findCategory(id));
    	return "editCategory.jsp";

    }
    
    
    // Edit put request
    @RequestMapping(value="/categorys/{id}/edit", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return  "editCategory.jsp";
        } else {
            categoryService.saveCategory(category);
            return "redirect:/home";
        }
        
        
        
    }
    
    
    // Delete Route
    
    
    

}
