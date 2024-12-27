package com.example.miaomiaohelp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId; // 帖子ID

    @Column(nullable = false)
    private Long userId; // 用户ID，关联到 users 表

    @Column(columnDefinition = "TEXT")
    private String textContent; // 文字内容

    private String imageUrl; // 图片链接

    private LocalDateTime createdAt = LocalDateTime.now(); // 创建时间
    private LocalDateTime updatedAt = LocalDateTime.now(); // 更新时间

    // Getter 和 Setter 方法
    public Long getPostId() { return postId; }
    public void setPostId(Long postId) { this.postId = postId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getTextContent() { return textContent; }
    public void setTextContent(String textContent) { this.textContent = textContent; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
