package com.admin.rcsquaretech.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.admin.rcsquaretech.DTO.BlogDTO;
import com.admin.rcsquaretech.DTO.BlogResponseDTO;
import com.admin.rcsquaretech.service.BlogService;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/getBlogs")
    public ResponseEntity<Page<BlogResponseDTO>> getAllBlogs(Pageable pageable) {
        return ResponseEntity.ok(blogService.getPublishedBlogs(pageable));
    }
    
    @GetMapping("/getDraftBlogs")
    public ResponseEntity<Page<BlogResponseDTO>> getAllDraftBlogs(Pageable pageable) {
        return ResponseEntity.ok(blogService.getDraftBlogs(pageable));
    }
    
    @GetMapping("/getBlog/{id}")
    public ResponseEntity<BlogResponseDTO> getBlogById(@PathVariable UUID id) {
        BlogResponseDTO blog = blogService.getBlogById(id);
        return ResponseEntity.ok(blog);
    }

    @PostMapping("/createBlog")
    public ResponseEntity<BlogResponseDTO> createBlog(@RequestBody BlogDTO blogDTO) {
        BlogResponseDTO createdBlog = blogService.createBlog(blogDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBlog);
    }

    @PutMapping("/updateBlog/{id}")
    public ResponseEntity<BlogResponseDTO> updateBlog(@PathVariable UUID id, @RequestBody BlogDTO blogDTO) {
        BlogResponseDTO updatedBlog = blogService.updateBlog(id, blogDTO);
        return ResponseEntity.ok(updatedBlog);
    }

    @DeleteMapping("/deleteBlog/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable UUID id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
} 