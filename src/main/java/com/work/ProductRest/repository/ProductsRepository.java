package com.work.ProductRest.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.ProductRest.model.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>{

	List<Products> findByTitleContainingIgnoreCase(String keyword);

	List<Products> findByCategoryIgnoreCase(String category);

	


}
