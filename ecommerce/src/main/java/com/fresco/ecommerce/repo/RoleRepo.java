package com.fresco.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fresco.ecommerce.models.Role;
@Repository
public interface RoleRepo extends JpaRepository<Role, Integer>{

}
