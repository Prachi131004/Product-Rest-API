package com.work.ProductRest.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Products {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;
	    private String description;
	    private String category;
	    private double price;
	    private double discountPercentage;
	    private double rating;
	    private int stock;
	    private String brand;
	    private String sku;         //Stock Keeping Unit, ex-TS-RED-M-001
	    private int weight;
	    private String warrantyInformation;
	    private String shippingInformation;
	    private String availabilityStatus;
	    private String returnPolicy;
	    private int minimumOrderQuantity;
	    
	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;

	    // Cloudinary
	    private String imageUrl;
	    private String publicId;
	    
	    private String thumbnail;
	    
	    // List fields
	    private List<String> tags;
	    private List<String> images;

	    // Embedded object
	    @Embedded
	    private Dimensions dimensions;

	    // One-to-Many relation
	    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonManagedReference
	    private List<Review> reviews;

		public Products() {
			super();
			
		}

		public Products(String title, String description, String category, double price, double discountPercentage,
				double rating, int stock, String brand, String sku, int weight, String warrantyInformation,
				String shippingInformation, String availabilityStatus, String returnPolicy, int minimumOrderQuantity,
				LocalDateTime createdAt, LocalDateTime updatedAt, String imageUrl, String publicId, String thumbnail,
				List<String> tags, List<String> images, Dimensions dimensions, List<Review> reviews) {
			super();
			this.title = title;
			this.description = description;
			this.category = category;
			this.price = price;
			this.discountPercentage = discountPercentage;
			this.rating = rating;
			this.stock = stock;
			this.brand = brand;
			this.sku = sku;
			this.weight = weight;
			this.warrantyInformation = warrantyInformation;
			this.shippingInformation = shippingInformation;
			this.availabilityStatus = availabilityStatus;
			this.returnPolicy = returnPolicy;
			this.minimumOrderQuantity = minimumOrderQuantity;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
			this.imageUrl = imageUrl;
			this.publicId = publicId;
			this.thumbnail = thumbnail;
			this.tags = tags;
			this.images = images;
			this.dimensions = dimensions;
			this.reviews = reviews;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public double getDiscountPercentage() {
			return discountPercentage;
		}

		public void setDiscountPercentage(double discountPercentage) {
			this.discountPercentage = discountPercentage;
		}

		public double getRating() {
			return rating;
		}

		public void setRating(double rating) {
			this.rating = rating;
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public String getSku() {
			return sku;
		}

		public void setSku(String sku) {
			this.sku = sku;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public String getWarrantyInformation() {
			return warrantyInformation;
		}

		public void setWarrantyInformation(String warrantyInformation) {
			this.warrantyInformation = warrantyInformation;
		}

		public String getShippingInformation() {
			return shippingInformation;
		}

		public void setShippingInformation(String shippingInformation) {
			this.shippingInformation = shippingInformation;
		}

		public String getAvailabilityStatus() {
			return availabilityStatus;
		}

		public void setAvailabilityStatus(String availabilityStatus) {
			this.availabilityStatus = availabilityStatus;
		}

		public String getReturnPolicy() {
			return returnPolicy;
		}

		public void setReturnPolicy(String returnPolicy) {
			this.returnPolicy = returnPolicy;
		}

		public int getMinimumOrderQuantity() {
			return minimumOrderQuantity;
		}

		public void setMinimumOrderQuantity(int minimumOrderQuantity) {
			this.minimumOrderQuantity = minimumOrderQuantity;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getPublicId() {
			return publicId;
		}

		public void setPublicId(String publicId) {
			this.publicId = publicId;
		}

		public String getThumbnail() {
			return thumbnail;
		}

		public void setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
		}

		public List<String> getTags() {
			return tags;
		}

		public void setTags(List<String> tags) {
			this.tags = tags;
		}

		public List<String> getImages() {
			return images;
		}

		public void setImages(List<String> images) {
			this.images = images;
		}

		public Dimensions getDimensions() {
			return dimensions;
		}

		public void setDimensions(Dimensions dimensions) {
			this.dimensions = dimensions;
		}

		public List<Review> getReviews() {
			return reviews;
		}

		public void setReviews(List<Review> reviews) {
			this.reviews = reviews;
		}

		@Override
		public String toString() {
			return "Products [id=" + id + ", title=" + title + ", description=" + description + ", category=" + category
					+ ", price=" + price + ", discountPercentage=" + discountPercentage + ", rating=" + rating
					+ ", stock=" + stock + ", brand=" + brand + ", sku=" + sku + ", weight=" + weight
					+ ", warrantyInformation=" + warrantyInformation + ", shippingInformation=" + shippingInformation
					+ ", availabilityStatus=" + availabilityStatus + ", returnPolicy=" + returnPolicy
					+ ", minimumOrderQuantity=" + minimumOrderQuantity + ", createdAt=" + createdAt + ", updatedAt="
					+ updatedAt + ", imageUrl=" + imageUrl + ", publicId=" + publicId + ", thumbnail=" + thumbnail
					+ ", tags=" + tags + ", images=" + images + "]";
		}	

}
