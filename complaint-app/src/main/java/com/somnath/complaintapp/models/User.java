package com.somnath.complaintapp.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CLIENT")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "USERNAME", unique = true, updatable = false, nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"))
	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE")
	private Set<Role> roles=new HashSet<>();
	
	@JsonManagedReference
	@OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
	private Member member;
}
