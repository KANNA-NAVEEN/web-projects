package com.somnath.complaintapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.somnath.complaintapp.daos.UserDao;
import com.somnath.complaintapp.dtos.UserDto;
import com.somnath.complaintapp.exceptions.InvalidIdException;
import com.somnath.complaintapp.models.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
	private final UserDao userDao;
	private final ModelMapper modelMapper;

	@PostMapping(value = "/login")
	public ResponseEntity<UserDto> login(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {
		User user = userDao.findByUsername(username).orElseThrow(() -> new InvalidIdException("invalid username"));
		if (!user.getPassword().equals(password)) {
			throw new InvalidIdException("invalid password");
		}
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return ResponseEntity.ok(userDto);
	}
}

