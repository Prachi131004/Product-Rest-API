package com.work.ProductRest.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.ProductRest.model.Products;
import com.work.ProductRest.model.Review;
import com.work.ProductRest.repository.ProductsRepository;
import com.work.ProductRest.repository.ReviewRepository;

@Service
public class ReviewService {
	
	private static final Logger log = LoggerFactory.getLogger(ReviewService.class);
	
	  @Autowired
	    private ReviewRepository reviewRepo;

	    @Autowired
	    private ProductsRepository productRepo;
	    
	    // Create review 
	    public Review createReview(
	            Long productId,
	            double rating,
	            String comment,
	            String reviewerName,
	            String reviewerEmail) {

	    	log.info("Creating review for productId: {}", productId);
	        Products product = productRepo.findById(productId)
	                .orElseThrow(() -> new RuntimeException("Product not found"));

	        Review review = new Review();
	        review.setRating(rating);
	        review.setComment(comment);
	        review.setReviewerName(reviewerName);
	        review.setReviewerEmail(reviewerEmail);
	        review.setDate(LocalDateTime.now());   

	        review.setProducts(product);

	        return reviewRepo.save(review);
	    }
	    
	    // Get all reviews
	    public List<Review> getAllReviews() {
	        return reviewRepo.findAll();
	    }

	    // Get reviews by ID
	    public Review getReviewById(Long id) {
	        return reviewRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Review not found"));
	    }

	    // Get reviews of a product
	    public List<Review> getReviewsByProduct(Long productId) {

	        Products product = productRepo.findById(productId)
	                .orElseThrow(() -> new RuntimeException("Product not found"));

	        return product.getReviews();
	    }

	 // UPDATE REVIEW
		public Review updateReview(Long id, double rating, String comment, String reviewerName, String reviewerEmail) {

	        log.info("Updating review id: {}", id);

	        Review review = reviewRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Review not found"));

	        // Update fields
	        review.setRating(rating);
	        review.setComment(comment);
	        review.setReviewerName(reviewerName);
	        review.setReviewerEmail(reviewerEmail);

	        // Update date
	        review.setDate(LocalDateTime.now());

	        Review updated = reviewRepo.save(review);

	        log.info("Review updated successfully");

	        return updated;
	    }
		
		// DELETE REVIEW
		public void deleteReview(Long id) {

		    log.info("Deleting review id: {}", id);

		    Review review = reviewRepo.findById(id)
		            .orElseThrow(() -> new RuntimeException("Review not found"));

		    reviewRepo.delete(review);

		    log.info("Review deleted successfully");
		}
}
