package com.fresco.ecommercedemo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fresco.ecommercedemo.models.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
}
