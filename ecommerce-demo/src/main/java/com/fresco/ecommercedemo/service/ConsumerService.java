package com.fresco.ecommercedemo.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fresco.ecommercedemo.models.Cart;
import com.fresco.ecommercedemo.models.CartProduct;
import com.fresco.ecommercedemo.models.Category;
import com.fresco.ecommercedemo.models.Product;
import com.fresco.ecommercedemo.models.User;
import com.fresco.ecommercedemo.repo.CartProductRepo;
import com.fresco.ecommercedemo.repo.CategoryRepo;
import com.fresco.ecommercedemo.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsumerService {
	private final UserRepo userRepo;
	private final CartProductRepo cartProductRepo;
	private final CategoryRepo categoryRepo;

	public ResponseEntity<Cart> getCart(String username) {
		Cart cart = userRepo.findByUsername(username).get().getCart();
		return ResponseEntity.ok(cart);
	}

	public ResponseEntity<Object> addProductToCart(Product productToBeAdded, String username) {
		User user = userRepo.findByUsername(username).get();
		Optional<CartProduct> optionalCartProduct = cartProductRepo
				.findByCartUserUserIdAndProductProductId(user.getUserId(), productToBeAdded.getProductId());
		if (optionalCartProduct.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		Cart cart = user.getCart();
		Optional<Category> optionalCategory = categoryRepo
				.findByCategoryName(productToBeAdded.getCategory().getCategoryName());
		if (optionalCategory.isPresent()) {
			productToBeAdded.setCategory(optionalCategory.get());
		}
		CartProduct cartProduct = new CartProduct();
		cartProduct.setCart(cart);
		cartProduct.setProduct(productToBeAdded);
		cartProductRepo.save(cartProduct);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Object> updateCartProduct(CartProduct cartProductToBeUpdated, String username) {
		Product product = cartProductToBeUpdated.getProduct();
		User user = userRepo.findByUsername(username).get();
		Cart cart = user.getCart();
		Optional<CartProduct> optionalCartProduct = cartProductRepo
				.findByCartUserUserIdAndProductProductId(user.getUserId(), product.getProductId());
		if (optionalCartProduct.isEmpty()) {
			cartProductToBeUpdated.setCart(cart);
			Optional<Category> optionalCategory = categoryRepo
					.findByCategoryName(product.getCategory().getCategoryName());
			if (optionalCategory.isPresent()) {
				product.setCategory(optionalCategory.get());
			}
			cartProductRepo.save(cartProductToBeUpdated);
		} else {
			CartProduct cartProduct = optionalCartProduct.get();
			if (cartProductToBeUpdated.getQuantity().equals(0)) {
				cartProductRepo.delete(cartProduct);
			} else {
				cartProduct.setQuantity(cartProductToBeUpdated.getQuantity());
				cartProductRepo.save(cartProduct);
			}
		}
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Object> deleteCartProduct(Product productToBeDeleted, String username) {
		User user = userRepo.findByUsername(username).get();
		Optional<CartProduct> optionalCartProduct = cartProductRepo
				.findByCartUserUserIdAndProductProductId(user.getUserId(), productToBeDeleted.getProductId());
		if (optionalCartProduct.isPresent()) {
			cartProductRepo.delete(optionalCartProduct.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
}
