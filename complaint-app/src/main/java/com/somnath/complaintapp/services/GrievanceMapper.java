package com.somnath.complaintapp.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.somnath.complaintapp.dtos.CommentDto;
import com.somnath.complaintapp.dtos.GrievanceDto;
import com.somnath.complaintapp.models.Comment;
import com.somnath.complaintapp.models.Grievance;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public final class GrievanceMapper {
	private final ModelMapper mapper = new ModelMapper();
	
	public GrievanceDto mapToDto(Grievance entity) {
		GrievanceDto dto = GrievanceDto.builder()
				.category(entity.getCategory())
				.origin(entity.getOrigin())
				.dateOfGrievance(entity.getDateOfGrievance())
				.dateOfResponse(entity.getDateOfResponse())
				.status(entity.getStatus())
				.comment(mapper.map(entity.getComment(), CommentDto.class))
				.build();
		return dto;
	}
	public Grievance mapToEntity(GrievanceDto dto) {
		Grievance entity = Grievance.builder()
				.category(dto.getCategory())
				.origin(dto.getOrigin())
				.dateOfGrievance(dto.getDateOfGrievance())
				.dateOfResponse(dto.getDateOfResponse())
				.status(dto.getStatus())
				.comment(mapper.map(dto.getComment(), Comment.class))
				.build();
		return entity;
	}
}
