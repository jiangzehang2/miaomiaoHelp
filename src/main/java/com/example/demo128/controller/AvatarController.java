package com.example.demo128.controller;


import com.example.demo128.Response.AvatarResponse;
import com.example.demo128.domain.User;
import com.example.demo128.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class AvatarController {

    @Autowired
    UserMapper userMapper;

    String path = "D:\\avatar";

    @PostMapping("/changeAvatar")


    public AvatarResponse changeAvatar(
            @RequestPart("手机号") String phoneNumber,
            @RequestPart("头像") MultipartFile file) {
        //创建响应对象
        AvatarResponse Response = new AvatarResponse();

        String fileName = file.getOriginalFilename();


        String filePath = path + "/" + fileName;
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User user = userMapper.selectuser(phoneNumber);
        user.setAvatar(filePath);
        userMapper.save(user);
        return Response;
    }


    @PostMapping(path = "/download")
    public @ResponseBody byte[] download(int id) throws IOException {
        String address = userMapper.findById(id).get().getAvatar();
        FileInputStream fis = new FileInputStream(address);
        return fis.readAllBytes();

    }
}