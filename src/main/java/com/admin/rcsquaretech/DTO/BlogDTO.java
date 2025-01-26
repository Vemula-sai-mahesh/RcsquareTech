package com.admin.rcsquaretech.DTO;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BlogDTO {
    private String title;
    private String content;
    private String category;
    private String coverImage;
    private LocalDateTime createdAt; // Adding createdAt field
    private Boolean draft; // Use Boolean instead of boolean
    private List<String> tags; // Tags as List of Strings

    // Constructor
    public BlogDTO(String title, String content, String category, String coverImage, Boolean draft, List<String> tags, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.coverImage = coverImage;
        this.draft = draft; // Handle null value
        this.tags = tags;
        this.createdAt = createdAt; // Initialize createdAt in constructor
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt; // Getter for createdAt
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt; // Setter for createdAt
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getDraft() {
        return draft; // Changed to Boolean
    }

    public void setDraft(Boolean draft) {
        this.draft = draft; // Changed to Boolean
    }
}
