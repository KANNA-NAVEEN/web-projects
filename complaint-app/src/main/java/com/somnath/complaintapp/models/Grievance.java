package com.somnath.complaintapp.models;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GRIEVANCES")
@Builder
public class Grievance implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "CATEGORY", nullable = false)
	private String category;
	
	@Column(name = "ORIGIN", nullable = false)
	private String origin;
	
	@Column(name = "DATE_OF_GRIEVANCE", nullable = false)
	private LocalDate dateOfGrievance;
	
	@Column(name = "DATE_OF_RESPONSE")
	private LocalDate dateOfResponse;
	
	@Column(name = "STATUS", nullable = false)
	private String status;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "COMMENT_ID", referencedColumnName = "id")
	private Comment comment;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID")
	private Member member;
}
