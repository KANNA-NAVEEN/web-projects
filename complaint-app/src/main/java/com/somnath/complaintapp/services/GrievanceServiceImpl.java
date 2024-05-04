package com.somnath.complaintapp.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.somnath.complaintapp.daos.CommentDao;
import com.somnath.complaintapp.daos.GrievanceDao;
import com.somnath.complaintapp.daos.MemberDao;
import com.somnath.complaintapp.dtos.CommentDto;
import com.somnath.complaintapp.dtos.GrievanceDto;
import com.somnath.complaintapp.exceptions.InvalidIdException;
import com.somnath.complaintapp.models.Comment;
import com.somnath.complaintapp.models.Grievance;
import com.somnath.complaintapp.models.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GrievanceServiceImpl implements GrievanceService {
	private final GrievanceDao grievanceDao;
	private final CommentDao commentDao;
	private final MemberDao memberDao;
	private final ModelMapper modelMapper;

	@Override
	public List<GrievanceDto> listGrievances() {
		return grievanceDao.findAll().stream().map(entity -> modelMapper.map(entity, GrievanceDto.class)).toList();
	}

	@Override
	public GrievanceDto grievanceById(Integer id) {
		Grievance grievance = grievanceDao.findById(id)
				.orElseThrow(() -> new InvalidIdException("Grievance id not found"));
		GrievanceDto grievanceDto = modelMapper.map(grievance, GrievanceDto.class);
		return grievanceDto;
	}

	@Override
	public GrievanceDto addGrievance(String username, GrievanceDto grievanceDto) {
		Member member = memberDao.findByUserUsername(username)
				.orElseThrow(() -> new InvalidIdException("Grievance cannot be added due to invalid member id"));
		Grievance grievance = modelMapper.map(grievanceDto, Grievance.class);
		if (Objects.nonNull(grievance.getComment())) {
			grievance.getComment().setType(grievance.getCategory());
		}
		grievance.setMember(member);
		grievance = grievanceDao.save(grievance);
		GrievanceDto addedGrievanceDto = modelMapper.map(grievance, GrievanceDto.class);
		return addedGrievanceDto;
	}

	@Override
	public GrievanceDto updateGrievance(Integer id, GrievanceDto grievanceToBeUpdatedDto) {
		Grievance grievanceToBeUpdated = grievanceDao.findById(id)
				.orElseThrow(() -> new InvalidIdException("Grievance cannot be updated due to Invalid grievance Id"));
		grievanceToBeUpdated.setCategory(grievanceToBeUpdatedDto.getCategory());
		grievanceToBeUpdated.setOrigin(grievanceToBeUpdatedDto.getOrigin());
		grievanceToBeUpdated.setDateOfGrievance(grievanceToBeUpdatedDto.getDateOfGrievance());
		grievanceToBeUpdated.setDateOfResponse(grievanceToBeUpdatedDto.getDateOfResponse());
		grievanceToBeUpdated.setStatus(grievanceToBeUpdatedDto.getStatus());
		CommentDto commentToBeUpdatedDto = grievanceToBeUpdatedDto.getComment();
		Comment commentToBeUpdated = grievanceToBeUpdated.getComment();
		if (Objects.nonNull(commentToBeUpdatedDto)) {
			if (Objects.isNull(commentToBeUpdated)) {
				commentToBeUpdated = new Comment();
				commentToBeUpdated.setType(grievanceToBeUpdatedDto.getCategory());
				grievanceToBeUpdated.setComment(commentToBeUpdated);
			}
			commentToBeUpdated.setComments(commentToBeUpdatedDto.getComments());
		} else if (Objects.nonNull(commentToBeUpdated)) {
			commentDao.delete(commentToBeUpdated);
			grievanceToBeUpdated.setComment(null);
		}
		Grievance updatedGrievance = grievanceDao.save(grievanceToBeUpdated);
		GrievanceDto updatedGrievanceDto = modelMapper.map(updatedGrievance, GrievanceDto.class);
		return updatedGrievanceDto;
	}

	@Override
	public GrievanceDto deleteGrievance(Integer id) {
		Grievance grievance = grievanceDao.findById(id)
				.orElseThrow(() -> new InvalidIdException("Grievance cannot be deleted due to Invalid Id"));
		grievanceDao.delete(grievance);
		GrievanceDto deletedGrievanceDto = modelMapper.map(grievance, GrievanceDto.class);
		return deletedGrievanceDto;
	}

	@Override
	public List<GrievanceDto> getSearchResults(String category, String memberName, String fromDate, String toDate) {
		List<GrievanceDto> grievanceDtos = grievanceDao
				.findBySearch(category, Objects.isNull(fromDate) ? null : LocalDate.parse(fromDate),
						Objects.isNull(toDate) ? null : LocalDate.parse(toDate))
				.stream().filter(entity -> {
					return Objects.isNull(memberName) || entity.getMember().getName().equals(memberName);
				}).map(entity -> modelMapper.map(entity, GrievanceDto.class)).toList();
		return grievanceDtos;
	}

	@Override
	public List<GrievanceDto> viewDetails(String username) {
		Member member = memberDao.findByUserUsername(username)
				.orElseThrow(() -> new InvalidIdException("user not found with username: %s".formatted(username)));
		List<GrievanceDto> grievanceDtos = grievanceDao.findByMemberId(member.getId()).stream().map(entity -> modelMapper.map(entity, GrievanceDto.class)).toList();
		return grievanceDtos;
	}
}
