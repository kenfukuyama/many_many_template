package com.kb.chitchat.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

// Domain Model
@Entity // Related to databases
@Table(name="products") // table name products
public class Product {
	// domain models allow us to create table
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;
	
    @NotNull
    @Size(min = 3, max = 200, message="name needs to be more than 3 characters")
    private String name;
    
    @NotNull
    @Size(min = 3, max = 40, message = "it has to be more than 3 characters")
    private String description;
    
    @NotNull
    @DecimalMin(value = "0.00", message = "Price needs to be greater than 0")
    private Double price;
    
    
    
    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
	// before it created
    @PrePersist 
    protected void onCreate(){
        this.createdAt = new Date();
    }
    // before it updates, null at first
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
    
    // many to one relationship
//  @ManyToOne(fetch=FetchType.LAZY)
//  @JoinColumn(name="user_id")
//  private User user;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name="categorys_products",
    		joinColumns = @JoinColumn(name = "product_id"),
    		inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categorys;
    // getter for setter for relationships too.
    
  
    
    // empty public constructor
    public Product() {}

    // overloaded constructor
	public Product(String name, String description, Double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
    // TODO add getter and setters for member variable
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public List<Category> getCategorys() {
		return categorys;
	}
	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}


}
