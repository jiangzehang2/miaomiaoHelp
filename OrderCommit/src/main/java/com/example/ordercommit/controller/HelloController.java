package com.example.ordercommit.controller;



import com.example.ordercommit.User;
import com.example.ordercommit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class HelloController {

    @RequestMapping(value="hello",method= RequestMethod.GET)
    @ResponseBody
    public String hello(){


        return "Hello World";
    }


    }
