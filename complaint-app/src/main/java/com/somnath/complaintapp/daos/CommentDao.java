package com.somnath.complaintapp.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.somnath.complaintapp.models.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {

}
