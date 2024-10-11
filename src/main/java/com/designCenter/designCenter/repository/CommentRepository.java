package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
