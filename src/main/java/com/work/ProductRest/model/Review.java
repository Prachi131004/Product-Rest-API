package com.work.ProductRest.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double rating;
    private String comment;
    private LocalDateTime date;
    private String reviewerName;
    private String reviewerEmail;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Products products;

	public Review() {
		super();
		
	}

	public Review(double rating, String comment, LocalDateTime date, String reviewerName, String reviewerEmail,
			Products products) {
		super();
		this.rating = rating;
		this.comment = comment;
		this.date = date;
		this.reviewerName = reviewerName;
		this.reviewerEmail = reviewerEmail;
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public String getReviewerEmail() {
		return reviewerEmail;
	}

	public void setReviewerEmail(String reviewerEmail) {
		this.reviewerEmail = reviewerEmail;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", rating=" + rating + ", comment=" + comment + ", date=" + date + ", reviewerName="
				+ reviewerName + ", reviewerEmail=" + reviewerEmail + "]";
	}

}
