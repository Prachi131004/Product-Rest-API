package com.work.ProductRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.work.ProductRest.model.Review;
import com.work.ProductRest.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	 @Autowired
	    private ReviewService service;

	    // CREATE REVIEW
	    @PostMapping("/products/{productId}")
	    public Review createReview(
	            @PathVariable Long productId,
	            @RequestParam double rating,
	            @RequestParam String comment,
	            @RequestParam String reviewerName,
	            @RequestParam String reviewerEmail) {

	        return service.createReview(
	                productId, rating, comment, reviewerName, reviewerEmail);
	    }
	    
	    // GET ALL REVIEWS
	    @GetMapping
	    public List<Review> getAllReviews() {
	        return service.getAllReviews();
	    }

	    // GET REVIEW BY ID
	    @GetMapping("/{id}")
	    public Review getReview(@PathVariable Long id) {
	        return service.getReviewById(id);
	    }

	    // GET REVIEWS BY PRODUCT
	    @GetMapping("/products/{productId}")
	    public List<Review> getReviewsByProduct(@PathVariable Long productId) {
	        return service.getReviewsByProduct(productId);
	    }

	 // UPDATE REVIEW
		@PutMapping("/{id}")
		public Review updateReview(@PathVariable Long id, @RequestParam int rating, @RequestParam String comment,
				@RequestParam String reviewerName, @RequestParam String reviewerEmail) {

			return service.updateReview(id, rating, comment, reviewerName, reviewerEmail);
		}
		
		// DELETE REVIEW
		@DeleteMapping("/{id}")
		public String deleteReview(@PathVariable Long id) {

		    service.deleteReview(id);

		    return "Review deleted successfully";
		} 
}
