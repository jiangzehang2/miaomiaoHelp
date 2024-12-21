package com.example.ordercommit.controller;


import com.example.ordercommit.Entity.Post;
import com.example.ordercommit.Entity.User;
import com.example.ordercommit.repository.PostRepository;
import com.example.ordercommit.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Post")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    String path="D:\\restore";
    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String create(@RequestParam("id") int userid,
                                       @RequestParam("text") String text,
                                       @RequestPart("image") MultipartFile file
                                       ) {
        Post post = new Post();
        User user= userRepository.findById(userid).get();
        post.setUser(user);
        post.setTextContent(text);

        String fileName=file.getOriginalFilename();
        String filePath=path+"/"+fileName;
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        post.setImageUrl(path+"/"+fileName);
        postRepository.save(post);


        return "success";

    }

    @GetMapping("/list")
    public @ResponseBody List<Post> getAllPosts() {

        return postRepository.findAll();
    }


    @Operation(description = "传入订单id，传回对应的图片")
    @GetMapping(path="/download")
    public @ResponseBody byte[] download(@RequestParam("id") int id) throws IOException{
        String address=postRepository.findById(id).get().getImageUrl();
        FileInputStream fis=new FileInputStream(address);
        return fis.readAllBytes();
    }
}
