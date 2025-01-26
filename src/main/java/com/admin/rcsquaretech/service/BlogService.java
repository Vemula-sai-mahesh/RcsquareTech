package com.admin.rcsquaretech.service;

import com.admin.rcsquaretech.DTO.BlogDTO;
import com.admin.rcsquaretech.DTO.BlogResponseDTO;
import com.admin.rcsquaretech.model.Blog;
import com.admin.rcsquaretech.repository.BlogRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Page<BlogResponseDTO> getPublishedBlogs(Pageable pageable) {
        Page<Blog> blogs = blogRepository.findByIsDraftFalse(pageable);
        return blogs.map(blog -> new BlogResponseDTO(blog.getId(), blog.getTitle(), blog.getContent(),
                blog.getCategory(), blog.getCoverImage(), blog.isDraft(), blog.getTags(), blog.getCreatedAt(), blog.getUpdatedAt()));
    }
    
    public Page<BlogResponseDTO> getDraftBlogs(Pageable pageable) {
        Page<Blog> blogs = blogRepository.findByIsDraftTrue(pageable);
        return blogs.map(blog -> new BlogResponseDTO(blog.getId(), blog.getTitle(), blog.getContent(),
                blog.getCategory(), blog.getCoverImage(), blog.isDraft(), blog.getTags(), blog.getCreatedAt(), blog.getUpdatedAt()));
    }
    
    public BlogResponseDTO getBlogById(UUID id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Blog not found"));
        return new BlogResponseDTO(blog.getId(), blog.getTitle(), blog.getContent(),
                blog.getCategory(), blog.getCoverImage(), blog.isDraft(), blog.getTags(), blog.getCreatedAt(), blog.getUpdatedAt());
    }


    public BlogResponseDTO createBlog(BlogDTO blogDTO) {
        Blog blog = new Blog();
        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        blog.setCategory(blogDTO.getCategory());
        blog.setCoverImage(blogDTO.getCoverImage());
        blog.setCreatedAt(blogDTO.getCreatedAt());
        if (blogDTO.getDraft() != null) { // Only Create if not null
        	blog.setDraft(blogDTO.getDraft());
        }
        blog.setTags(blogDTO.getTags());

        Blog savedBlog = blogRepository.save(blog);
        return new BlogResponseDTO(savedBlog.getId(), savedBlog.getTitle(), savedBlog.getContent(),
                savedBlog.getCategory(), savedBlog.getCoverImage(), savedBlog.isDraft(), savedBlog.getTags(), savedBlog.getCreatedAt(), savedBlog.getUpdatedAt());
    }

    public BlogResponseDTO updateBlog(UUID id, BlogDTO blogDTO) {
        Blog existingBlog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Blog not found")); // Directly using IllegalArgumentException

        existingBlog.setTitle(blogDTO.getTitle());
        existingBlog.setContent(blogDTO.getContent());
        existingBlog.setCategory(blogDTO.getCategory());
        existingBlog.setCoverImage(blogDTO.getCoverImage());
        if (blogDTO.getDraft() != null) { // Only update if not null
            existingBlog.setDraft(blogDTO.getDraft());
        }
        existingBlog.setTags(blogDTO.getTags());

        Blog updatedBlog = blogRepository.save(existingBlog);
        return new BlogResponseDTO(updatedBlog.getId(), updatedBlog.getTitle(), updatedBlog.getContent(),
                updatedBlog.getCategory(), updatedBlog.getCoverImage(), updatedBlog.isDraft(), updatedBlog.getTags(), updatedBlog.getCreatedAt(), updatedBlog.getUpdatedAt());
    }

    public void deleteBlog(UUID id) {
        blogRepository.deleteById(id);
    }
}
