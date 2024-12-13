package com.example.demo128.controller;

import com.example.demo128.Response.RegisterResponse;
import com.example.demo128.Response.LoginResponse;
import com.example.demo128.domain.User;
import com.example.demo128.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    public LoginResponse login(String name, String password) {
        // 创建响应对象
        LoginResponse response = new LoginResponse();

        // 校验用户名和密码是否为空
        if (name == null || name.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("用户名不允许为空");
            return response;
        }
        if (password == null || password.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("密码不能为空");
            return response;
        }

        // 查询用户信息
        User user=userMapper.selectuser(name);
        if (user == null) {
            response.setSuccess(false);
            response.setMessage("用户不存在");
            return response;
        }

        // 校验密码
        if (Objects.equals(user.getPassword(), password)) {
            response.setSuccess(true);
            response.setMessage("登录成功");
            response.setToken("生成的令牌"); // 替换为实际生成的 token
        } else {
            response.setSuccess(false);
            response.setMessage("密码错误");
        }

        return response;
    }

    @GetMapping("/register")
    public RegisterResponse register(String phoneNumber, String password) {
        // 创建响应对象
        RegisterResponse response = new RegisterResponse();

        // 校验用户名和密码是否为空
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("用户名不允许为空");
            return response;
        }
        if (password == null || password.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("密码不能为空");
            return response;
        }

        // 校验密码强度（可根据实际需求进行密码强度验证）
        if (password.length() < 6) {
            response.setSuccess(false);
            response.setMessage("密码长度至少为 6 位");
            return response;
        }

        // 查询用户是否已存在
        User existingUser =userMapper.selectuser(phoneNumber);
        if (existingUser != null) {
            response.setSuccess(false);
            response.setMessage("用户名已存在");
            return response;
        }



        // 创建新用户
        User newUser = new User();
        newUser.setphoneNumber(phoneNumber);
        newUser.setPassword(password);

        // 保存新用户
        int result = userMapper.saveUser(phoneNumber,password);
        if (result > 0) {
            response.setSuccess(true);
            response.setMessage("注册成功");
        } else {
            response.setSuccess(false);
            response.setMessage("注册失败，请稍后再试");
        }

        return response;
    }
}
