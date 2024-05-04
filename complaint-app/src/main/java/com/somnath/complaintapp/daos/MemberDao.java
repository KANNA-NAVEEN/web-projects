package com.somnath.complaintapp.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.somnath.complaintapp.models.Member;

@Repository
public interface MemberDao extends JpaRepository<Member, Integer> {

	Optional<Member> findByUserUsername(String username);
}
