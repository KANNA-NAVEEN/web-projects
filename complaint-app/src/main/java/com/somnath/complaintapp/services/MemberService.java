package com.somnath.complaintapp.services;

import java.util.List;

import com.somnath.complaintapp.dtos.MemberDto;
import com.somnath.complaintapp.exceptions.InvalidIdException;

public interface MemberService {

	List<MemberDto> listMembers();

	MemberDto getMemebrById(Integer id);

	MemberDto addMember(String username, MemberDto memberDto);

	MemberDto updateMember(Integer id, MemberDto memberDto);

	MemberDto deleteMember(Integer id) throws InvalidIdException;
}
