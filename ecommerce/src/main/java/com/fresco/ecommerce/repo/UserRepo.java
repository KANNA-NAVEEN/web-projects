package com.fresco.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fresco.ecommerce.models.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}
