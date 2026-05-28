package com.work.ProductRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.work.ProductRest.model.Products;
import com.work.ProductRest.service.ProductsService;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	  @Autowired
	    private ProductsService service;

	    // GET ALL PRODUCTS
	    @GetMapping
	    public List<Products> getAllProducts() {
	        return service.getAll();
	    }

	    // GET PRODUCT BY ID
	    @GetMapping("/{id}")
	    public Products getProduct(@PathVariable Long id) {
	        return service.getOne(id);
	    }


	 // CREATE PRODUCT
	    @PostMapping
	    public Products createProduct(

	            @RequestParam String title,
	            @RequestParam String description,
	            @RequestParam String category,
	            @RequestParam double price,
	            @RequestParam double discountPercentage,
	            @RequestParam double rating,
	            @RequestParam int stock,
	            @RequestParam String brand,
	            @RequestParam String sku,
	            @RequestParam int weight,

	            @RequestParam String warrantyInformation,
	            @RequestParam String shippingInformation,
	            @RequestParam String returnPolicy,
	            @RequestParam int minimumOrderQuantity,

	            @RequestParam List<String> tags,
	            
	            @RequestParam double width,
	            @RequestParam double height,
	            @RequestParam double depth,

	            @RequestParam List<MultipartFile> files

	    ) {

	        return service.createProduct(
	                title,
	                description,
	                category,
	                price,
	                discountPercentage,
	                rating,
	                stock,
	                brand,
	                sku,
	                weight,
	                warrantyInformation,
	                shippingInformation,
	                returnPolicy,
	                minimumOrderQuantity,
	                tags,
	                width,
	                height,
	                depth,
	                files
	        );
	    }
	    
	    // UPDATE PRODUCT
	    @PutMapping("/{id}")
	    public Products updateProduct(

	            @PathVariable Long id,

	            @RequestParam String title,
	            @RequestParam String description,
	            @RequestParam String category,
	            @RequestParam double price,
	            @RequestParam double discountPercentage,
	            @RequestParam double rating,
	            @RequestParam int stock,
	            @RequestParam String brand,
	            @RequestParam String sku,
	            @RequestParam int weight,

	            @RequestParam String warrantyInformation,
	            @RequestParam String shippingInformation,
	            @RequestParam String returnPolicy,
	            @RequestParam int minimumOrderQuantity,

	            @RequestParam List<String> tags,
	            @RequestParam double width,
	            @RequestParam double height,
	            @RequestParam double depth

	    ) {

	        return service.updateProduct(
	                id,
	                title,
	                description,
	                category,
	                price,
	                discountPercentage,
	                rating,
	                stock,
	                brand,
	                sku,
	                weight,
	                warrantyInformation,
	                shippingInformation,
	                returnPolicy,
	                minimumOrderQuantity,
	                tags,
	                width,
	                height,
	                depth
	        );
	    }
	    
	 // DELETE PRODUCT
	    @DeleteMapping("/{id}")
	    public String deleteProduct(@PathVariable Long id) {
	        return service.deleteProduct(id);
	    }

	 // SEARCH PRODUCT
	    @GetMapping("/search")
	    public List<Products> searchProducts(@RequestParam String keyword) {
	        return service.searchProducts(keyword);
	    }
	    
	    // FILTER BY CATEGORY
	    @GetMapping("/category/{category}")
	    public List<Products> getByCategory(@PathVariable String category) {
	        return service.getByCategory(category);
	    }

	    // PAGINATION + SORTING
	    @GetMapping("/page")
	    public Page<Products> getProductsPage(
	            @RequestParam int page,
	            @RequestParam int size,
	            @RequestParam String sortBy
	    ) {
	        return service.getProductsPage(page, size, sortBy);
	    }
}	
