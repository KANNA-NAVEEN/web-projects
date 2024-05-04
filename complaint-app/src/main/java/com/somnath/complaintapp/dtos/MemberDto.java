package com.somnath.complaintapp.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MemberDto {
	private Integer id;
	private String name;
	private LocalDate dateOfBirth;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String phoneNumber;
	private String language;
	private UserDto user;
}
