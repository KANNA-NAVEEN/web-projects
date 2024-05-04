package com.somnath.complaintapp.daos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.somnath.complaintapp.models.Grievance;

import jakarta.websocket.server.PathParam;

@Repository
public interface GrievanceDao extends JpaRepository<Grievance, Integer> {

	@Query(value = "from Grievance where (:category is null or category=:category) and (:fromDate is null or dateOfGrievance>=:fromDate and dateOfGrievance<=:toDate) ")
	List<Grievance> findBySearch(@PathParam(value = "category") String category,
			@PathParam(value = "fromDate") LocalDate fromDate, @PathParam(value = "toDate") LocalDate toDate);

	List<Grievance> findByMemberId(Integer id);
}
