package com.somnath.complaintapp.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.somnath.complaintapp.dtos.GrievanceDto;
import com.somnath.complaintapp.dtos.MemberDto;
import com.somnath.complaintapp.services.GrievanceService;
import com.somnath.complaintapp.services.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = { "api/v1/members", "api/v1/users" })
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class MemberController {
	private final MemberService memberService;
	private final GrievanceService grievanceService;

	@GetMapping
	public ResponseEntity<List<MemberDto>> listMembers() {
		List<MemberDto> membersDots = memberService.listMembers();
		return ResponseEntity.ok(membersDots);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<MemberDto> getMemberById(@PathVariable(name = "id") Integer id) {
		MemberDto memberDto = memberService.getMemebrById(id);
		return ResponseEntity.ok(memberDto);
	}

	@PostMapping(value = "{username}/members")
	public ResponseEntity<MemberDto> addMember(@PathVariable(name = "username") String username,
			@RequestBody MemberDto memberToBeAddedDto) {
		MemberDto addedMemberDto = memberService.addMember(username, memberToBeAddedDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedMemberDto);
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<MemberDto> updateMember(@PathVariable(name = "id") Integer id,
			@RequestBody MemberDto memberToBeUpdatedDto) {
		MemberDto updatedMemberDto = memberService.updateMember(id, memberToBeUpdatedDto);
		return ResponseEntity.ok(updatedMemberDto);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<MemberDto> deleteMember(@PathVariable(name = "id") Integer id) {
		MemberDto deletedMemberDto = memberService.deleteMember(id);
		return ResponseEntity.ok(deletedMemberDto);
	}

	@GetMapping(value = "grievances")
	public ResponseEntity<List<GrievanceDto>> listGrievances() {
		List<GrievanceDto> grievances = grievanceService.listGrievances();
		return ResponseEntity.ok(grievances);
	}

	@GetMapping(value = "grievances/{id}")
	public ResponseEntity<GrievanceDto> grievanceById(@PathVariable(name = "id") Integer id) {
		GrievanceDto grievanceDto = grievanceService.grievanceById(id);
		return ResponseEntity.ok(grievanceDto);
	}

	@PostMapping(value = "{username}/grievances")
	public ResponseEntity<GrievanceDto> addGrievance(@PathVariable(name = "username") String username,
			@RequestBody GrievanceDto grievanceToBeAddedDto) {
		GrievanceDto addedGrievanceDto = grievanceService.addGrievance(username, grievanceToBeAddedDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedGrievanceDto);
	}

	@PutMapping(value = "grievances/{id}")
	public ResponseEntity<GrievanceDto> updateGrievance(@PathVariable(name = "id") Integer id,
			@RequestBody GrievanceDto grievanceToBeUpdatedDto) {
		GrievanceDto updatedGrievanceDto = grievanceService.updateGrievance(id, grievanceToBeUpdatedDto);
		return ResponseEntity.ok(updatedGrievanceDto);
	}

	@DeleteMapping(value = "grievances/{id}")
	public ResponseEntity<GrievanceDto> deleteGrievance(@PathVariable(name = "id") Integer id) {
		GrievanceDto deletedGrievanceDto = grievanceService.deleteGrievance(id);
		return ResponseEntity.ok(deletedGrievanceDto);
	}

	@GetMapping(value = "grievances/search")
	public ResponseEntity<List<GrievanceDto>> getSearchResults(
			@RequestParam(name = "category", required = false) String category,
			@RequestParam(name = "memberName", required = false) String memberName,
			@RequestParam(name = "fromDate", required = false) String fromDate,
			@RequestParam(name = "toDate", required = false) String toDate) {
		List<GrievanceDto> grievanceDtos = grievanceService.getSearchResults(category, memberName, fromDate, toDate);
		return ResponseEntity.ok(grievanceDtos);
	}
	
	@GetMapping(value = "{username}/view-details")
	public ResponseEntity<List<GrievanceDto>> viewDetails(@PathVariable(name = "username") String username){
		List<GrievanceDto> grievanceDtos=grievanceService.viewDetails(username);
		return ResponseEntity.ok(grievanceDtos);
	}
}
