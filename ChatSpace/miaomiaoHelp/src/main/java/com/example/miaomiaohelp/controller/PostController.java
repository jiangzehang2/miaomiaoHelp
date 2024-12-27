package com.example.miaomiaohelp.controller;

import com.example.miaomiaohelp.entity.Post;
import com.example.miaomiaohelp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";

    /**
     * 创建新帖子
     * @param userId 用户ID
     * @param textContent 文字内容
     * @param image 图片文件
     * @return 创建的帖子
     * @throws IOException 图片保存异常
     */
    @PostMapping("/create")
    public Post createPost(
            @RequestParam("userId") Long userId,
            @RequestParam("textContent") String textContent,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) throws IOException {
        String imageUrl = null;

        // 保存图片到服务器
        if (image != null && !image.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            File file = new File(UPLOAD_DIR + fileName);
            file.getParentFile().mkdirs(); // 确保目录存在
            image.transferTo(file);
            imageUrl = "/uploads/" + fileName; // 图片链接
        }

        // 构造 Post 实体
        Post post = new Post();
        post.setUserId(userId);
        post.setTextContent(textContent);
        post.setImageUrl(imageUrl);

        return postService.savePost(post); // 保存帖子
    }

    /**
     * 获取所有帖子
     * @return 帖子列表
     */
    @GetMapping("/list")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
}
