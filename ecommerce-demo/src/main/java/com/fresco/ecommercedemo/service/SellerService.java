package com.fresco.ecommercedemo.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fresco.ecommercedemo.models.Category;
import com.fresco.ecommercedemo.models.Product;
import com.fresco.ecommercedemo.models.User;
import com.fresco.ecommercedemo.repo.CategoryRepo;
import com.fresco.ecommercedemo.repo.ProductRepo;
import com.fresco.ecommercedemo.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerService {
	private final UserRepo userRepo;
	private final ProductRepo productRepo;
	private final CategoryRepo categoryRepo;

	public ResponseEntity<List<Product>> getAllProducts(String username) {
		List<Product> products = userRepo.findByUsername(username).get().getProducts();
		return ResponseEntity.ok(products);
	}

	public ResponseEntity<Product> getProduct(Integer productId, String username) {
		User seller = userRepo.findByUsername(username).get();
		Optional<Product> optionalProduct = productRepo.findBySellerUserIdAndProductId(seller.getUserId(), productId);
		if (optionalProduct.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optionalProduct.get());
	}

	public ResponseEntity<Object> addProduct(Product productToBeAdded, String username) throws URISyntaxException {
		User seller = userRepo.findByUsername(username).get();
		productToBeAdded.setSeller(seller);
		Optional<Category> optionalCategory = categoryRepo
				.findByCategoryName(productToBeAdded.getCategory().getCategoryName());
		if (optionalCategory.isPresent()) {
			productToBeAdded.setCategory(optionalCategory.get());
		}
		 else {
		 categoryRepo.saveAndFlush(productToBeAdded.getCategory());
		 }
		Product product = productRepo.saveAndFlush(productToBeAdded);
		return ResponseEntity.created(new URI("http://localhost/api/auth/seller/product/" + product.getProductId()))
				.build();
	}

	public ResponseEntity<Object> updateProduct(Product product, String username) {
		User seller = userRepo.findByUsername(username).get();
		Optional<Product> optionalProduct = productRepo.findBySellerUserIdAndProductId(seller.getUserId(),
				product.getProductId());
		if (optionalProduct.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Product productToBeUpdated = optionalProduct.get();
		productToBeUpdated.setProductName(product.getProductName());
		productToBeUpdated.setPrice(product.getPrice());
		productToBeUpdated.setCategory(product.getCategory());
		Optional<Category> optionalCategory = categoryRepo
				.findByCategoryName(productToBeUpdated.getCategory().getCategoryName());
		if (optionalCategory.isPresent()) {
			productToBeUpdated.setCategory(optionalCategory.get());
		}
		productRepo.save(productToBeUpdated);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Object> deleteProduct(Integer productId, String username) {
		User seller = userRepo.findByUsername(username).get();
		Optional<Product> optionalProduct = productRepo.findBySellerUserIdAndProductId(seller.getUserId(), productId);
		if (optionalProduct.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		productRepo.delete(optionalProduct.get());
		optionalProduct = productRepo.findBySellerUserIdAndProductId(seller.getUserId(), productId);
		return ResponseEntity.ok().build();
	}

}
