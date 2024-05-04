package com.somnath.complaintapp.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GrievanceDto {
	private Integer id;
	private String category;
	private String origin;
	private LocalDate dateOfGrievance;
	private LocalDate dateOfResponse;
	private String status;
	private CommentDto comment;
	private MemberDto member;
}
