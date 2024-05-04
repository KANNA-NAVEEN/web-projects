package com.somnath.complaintapp.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MEMBERS")
@Builder
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "DATE_OF_BIRTH", nullable = false)
	private LocalDate dateOfBirth;
	
	@Column(name = "ADDRESS", nullable = false)
	private String address;
	
	@Column(name = "CITY", nullable = false)
	private String city;
	
	@Column(name = "STATE", nullable = false)
	private String state;
	
	@Column(name = "ZIPCODE", nullable = false)
	private String zipcode;

	@Column(name = "PHONE_NUMBER", nullable = false)
	private String phoneNumber;
	
	@Column(name = "LANGUAGE", nullable = false)
	private String language;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade =CascadeType.REMOVE, orphanRemoval = true)
	private List<Grievance> grievances;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "USERNAME", referencedColumnName = "username")
	private User user;
}
