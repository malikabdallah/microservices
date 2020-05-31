package com.sha.microservicemuseemanagement.repository;

import com.sha.microservicemuseemanagement.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepo extends JpaRepository<Comment, Long> {

}
