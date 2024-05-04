package com.fresco.ecommercedemo.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id","product_id"}))
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cpId;
	
	@ManyToOne
	@JoinColumn(name = "cart_id",referencedColumnName = "cartId")
	@JsonIgnore
	private Cart cart;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
	@JoinColumn(name = "product_id", referencedColumnName = "productId")
	private Product product;
	
	private Integer quantity=1;
}
