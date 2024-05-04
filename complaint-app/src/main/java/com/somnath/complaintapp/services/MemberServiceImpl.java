package com.somnath.complaintapp.services;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.somnath.complaintapp.daos.MemberDao;
import com.somnath.complaintapp.daos.UserDao;
import com.somnath.complaintapp.dtos.MemberDto;
import com.somnath.complaintapp.exceptions.AlreadyExistsException;
import com.somnath.complaintapp.exceptions.InvalidIdException;
import com.somnath.complaintapp.models.Member;
import com.somnath.complaintapp.models.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberDao memberDao;
	private final UserDao userDao;
	private final ModelMapper modelMapper;

	@Override
	public List<MemberDto> listMembers() {
		return memberDao.findAll().stream().map(entity -> modelMapper.map(entity, MemberDto.class)).toList();
	}

	@Override
	public MemberDto getMemebrById(Integer id) {
		Member member = memberDao.findById(id).orElseThrow(() -> new InvalidIdException("Member id not found"));
		return modelMapper.map(member, MemberDto.class);
	}

	@Override
	public MemberDto addMember(String username, MemberDto memberDto){
		User user = userDao.findByUsername(username)
				.orElseThrow(() -> new InvalidIdException("Member cannot be added due to user not found"));
		if (Objects.nonNull(user.getMember())) {
			throw new AlreadyExistsException("Member details already existed for given user");
		}
		Member member = modelMapper.map(memberDto, Member.class);
		member.setUser(user);
		member = memberDao.save(member);
		MemberDto addedMemberDto = modelMapper.map(member, MemberDto.class);
		return addedMemberDto;
	}

	@Override
	public MemberDto updateMember(Integer id, MemberDto memberToBeUpdatedDto) {
		Member memberToBeUpdated = memberDao.findById(id)
				.orElseThrow(() -> new InvalidIdException("Member cannot be updated due to Invalid member id"));
		memberToBeUpdated.setName(memberToBeUpdatedDto.getName());
		memberToBeUpdated.setDateOfBirth(memberToBeUpdatedDto.getDateOfBirth());
		memberToBeUpdated.setAddress(memberToBeUpdatedDto.getAddress());
		memberToBeUpdated.setCity(memberToBeUpdatedDto.getCity());
		memberToBeUpdated.setState(memberToBeUpdatedDto.getState());
		memberToBeUpdated.setZipcode(memberToBeUpdatedDto.getZipcode());
		memberToBeUpdated.setPhoneNumber(memberToBeUpdatedDto.getPhoneNumber());
		memberToBeUpdated.setLanguage(memberToBeUpdatedDto.getLanguage());
		Member updatedMember = memberDao.save(memberToBeUpdated);
		MemberDto updatedMemeberDto = modelMapper.map(updatedMember, MemberDto.class);
		return updatedMemeberDto;
	}

	@Override
	public MemberDto deleteMember(Integer id) {
		Member member = memberDao.findById(id)
				.orElseThrow(() -> new InvalidIdException("Member cannot be deleted due to Invalid member id"));
		memberDao.delete(member);
		MemberDto deletedMemberDto = modelMapper.map(member, MemberDto.class);
		return deletedMemberDto;
	}
}
