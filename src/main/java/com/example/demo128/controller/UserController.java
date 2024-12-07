package com.example.demo128.controller;

import ch.qos.logback.core.util.StringUtil;
import com.example.demo128.domain.User;
import com.example.demo128.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@Slf4j
@RequestMapping("user")

@RestController
public class UserController {

    @Autowired
    private UserMapper  userMapper;


    @GetMapping("login")
    public  String login(String  name,String password){

        User user=userMapper.selectuser(name);

        if(StringUtil.isNullOrEmpty(name)){
            return "用户名不允许为空";
        }
        if(StringUtil.isNullOrEmpty(password)){
            return "密码不能为空";
        }


        return"登录成功";
    }

    @GetMapping("register")
    public String register(String  name,String password){
   // log.info("name:{}",name);
  //  log.info("password:{}",password);
        if(StringUtil.isNullOrEmpty(name)){
            return "用户名不允许为空";
        }
            if(StringUtil.isNullOrEmpty(password)){
                return "密码不能为空";
            }


    int resultCount=userMapper.saveUser(name,password);

    if(resultCount==0)
        return "注册失败";


   return "注册成功";
    }
}
