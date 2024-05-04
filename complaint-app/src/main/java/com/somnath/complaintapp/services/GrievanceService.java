package com.somnath.complaintapp.services;

import java.util.List;

import com.somnath.complaintapp.dtos.GrievanceDto;

public interface GrievanceService {
	public List<GrievanceDto> listGrievances();

	public GrievanceDto grievanceById(Integer id);
	
	GrievanceDto addGrievance(String username, GrievanceDto grievanceDto);

	GrievanceDto updateGrievance(Integer id, GrievanceDto grievanceDto);

	GrievanceDto deleteGrievance(Integer id);

	public List<GrievanceDto> getSearchResults(String category, String memberName, String fromDate, String toDate);

	public List<GrievanceDto> viewDetails(String username);
}
