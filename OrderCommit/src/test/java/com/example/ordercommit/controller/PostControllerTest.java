package com.example.ordercommit.controller;

import com.example.ordercommit.Entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostControllerTest {

    PostController postController = new PostController();
    @Test
    void create() {
        MultipartFile file = null;
        postController.create(2,"this is a test",file);
    }

    @Test
    void getAllPosts() {
        List<Post> posts = postController.getAllPosts();
        posts=postController.getAllPosts();
        System.out.println(posts);
    }
}