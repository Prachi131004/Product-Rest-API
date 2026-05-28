package com.work.ProductRest.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.work.ProductRest.model.Dimensions;
import com.work.ProductRest.model.Products;
import com.work.ProductRest.repository.ProductsRepository;

@Service
public class ProductsService {
	
	private static final Logger log = LoggerFactory.getLogger(ProductsService.class);

	@Autowired
	private ProductsRepository repo;

	@Autowired
	private Cloudinary cloudinary;

	// GET ALL
	public List<Products> getAll() {
		return repo.findAll();
	}
	    
	// GET ONE
	public Products getOne(Long id) {

		log.info("Getting product with id: {}", id);

		return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	}
	
	// CREATE PRODUCT
	public Products createProduct(

            String title,
            String description,
            String category,
            double price,
            double discountPercentage,
            double rating,
            int stock,
            String brand,
            String sku,
            int weight,

            String warrantyInformation,
            String shippingInformation,
            String returnPolicy,
            int minimumOrderQuantity,

            List<String> tags,
            
            double width,
            double height,
            double depth,

            List<MultipartFile> files

    ) {

        log.info("Creating product: {}", title);

        Products product = new Products();
        
        // BASIC FIELDS
        product.setTitle(title);
        product.setDescription(description);
        product.setCategory(category);
        product.setPrice(price);
        product.setDiscountPercentage(discountPercentage);
        product.setRating(rating);
        product.setStock(stock);
        product.setBrand(brand);
        product.setSku(sku);
        product.setWeight(weight);

        // EXTRA FIELDS
        product.setWarrantyInformation(warrantyInformation);
        product.setShippingInformation(shippingInformation);
        product.setReturnPolicy(returnPolicy);
        product.setMinimumOrderQuantity(minimumOrderQuantity);

     // AUTO STOCK STATUS
        if (stock <= 5) {
            product.setAvailabilityStatus("Low Stock");
        } else {
            product.setAvailabilityStatus("In Stock");
        }

        // DATES
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        // TAGS
        product.setTags(tags != null ? tags : List.of());

        // DIMENSIONS
        Dimensions dim = new Dimensions();
        dim.setWidth(width);
        dim.setHeight(height);
        dim.setDepth(depth);

        product.setDimensions(dim);

        // MULTIPLE IMAGE UPLOAD
        List<String> imageUrls = new ArrayList<>();
        List<String> publicIds = new ArrayList<>();
        
        try {

            if (files != null && !files.isEmpty()) {

                for (MultipartFile file : files) {

                    if (!file.isEmpty()) {

                        Map upload = cloudinary.uploader()
                                .upload(file.getBytes(),
                                        ObjectUtils.emptyMap());

                        String imageUrl =
                                upload.get("secure_url").toString();

                        String publicId =
                                upload.get("public_id").toString();

                        imageUrls.add(imageUrl);
                        publicIds.add(publicId);
                    }
                }

                // MAIN IMAGE
                if (!imageUrls.isEmpty()) {

                    product.setImageUrl(imageUrls.get(0));
                    product.setPublicId(publicIds.get(0));

                    // THUMBNAIL
                    product.setThumbnail(imageUrls.get(0));
                }

                // SAVE ALL IMAGES
                product.setImages(imageUrls);
            }

        } catch (IOException e) {

            log.error("Image upload failed: {}", e.getMessage());

            throw new RuntimeException("Image upload failed");
        }
        
        Products saved = repo.save(product);

        log.info("Product created with id: {}", saved.getId());

        return saved;
    }
	
	// UPDATE PRODUCT
	public Products updateProduct(Long id, String title, String description, String category, double price,
			double discountPercentage, double rating, int stock, String brand, String sku, int weight,
			String warrantyInformation, String shippingInformation, String returnPolicy, int minimumOrderQuantity,
			List<String> tags, double width, double height, double depth) {

        log.info("Updating product with id: {}", id);

        Products product = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        product.setTitle(title);
        product.setDescription(description);
        product.setCategory(category);
        product.setPrice(price);
        product.setDiscountPercentage(discountPercentage);
        product.setRating(rating);
        product.setStock(stock);
        product.setBrand(brand);
        product.setSku(sku);
        product.setWeight(weight);
        
        product.setWarrantyInformation(warrantyInformation);
        product.setShippingInformation(shippingInformation);
        product.setReturnPolicy(returnPolicy);
        product.setMinimumOrderQuantity(minimumOrderQuantity);

        // AUTO STOCK STATUS
        if (stock <= 5) {
            product.setAvailabilityStatus("Low Stock");
        } else {
            product.setAvailabilityStatus("In Stock");
        }

        // UPDATE DATE
        product.setUpdatedAt(LocalDateTime.now());

        // TAGS
        product.setTags(tags);

        // DIMENSIONS
        Dimensions dim = product.getDimensions();
        
        dim.setWidth(width);
        dim.setHeight(height);
        dim.setDepth(depth);

        product.setDimensions(dim);

        return repo.save(product);
    }
    
    // DELETE PRODUCT
    public String deleteProduct(Long id) {

        log.info("Deleting product with id: {}", id);

        Products product = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        repo.delete(product);

        return "Product deleted successfully";
    }
    
 // SEARCH
    public List<Products> searchProducts(String keyword) {
        return repo.findByTitleContainingIgnoreCase(keyword);
    }
    
    // CATEGORY FILTER
    public List<Products> getByCategory(String category) {
        return repo.findByCategoryIgnoreCase(category);
    }
    
    // PAGINATION + SORTING
    public Page<Products> getProductsPage(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return repo.findAll(pageable);
    }
    
   
 }
