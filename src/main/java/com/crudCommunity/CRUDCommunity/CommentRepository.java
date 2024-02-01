package com.crudCommunity.CRUDCommunity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPostNumber(Long postNumber);
}
