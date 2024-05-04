package com.somnath.complaintapp.dtos;

import java.util.HashSet;
import java.util.Set;

import com.somnath.complaintapp.models.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	private Integer id;
	private String username;
	@Builder.Default
	private Set<Role> roles=new HashSet<>();
}
