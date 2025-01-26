package com.admin.rcsquaretech.repository;  

import com.admin.rcsquaretech.model.Blog;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, UUID> {
    Page<Blog> findByIsDraftFalse(Pageable pageable); // Get only published blogs
    Page<Blog> findByCategory(String category, Pageable pageable); // Get blogs by category
    Page<Blog> findByTagsContaining(String tag, Pageable pageable); // Search blogs by tag
	Page<Blog> findByIsDraftTrue(Pageable pageable);
}