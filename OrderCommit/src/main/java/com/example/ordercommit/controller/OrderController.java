package com.example.ordercommit.controller;


import com.example.ordercommit.Order;
import com.example.ordercommit.User;
import com.example.ordercommit.repository.OrderRepository;
import com.example.ordercommit.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Tag(name ="商家管理接口")
@Controller
@RequestMapping(path = "/Order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    private UserRepository userRepository;
    String path="D:\\restore";
    @Operation(description = "发布一个新的订单")
    @PostMapping(path="/add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String addOrder(@RequestParam(value = "序列号") int userId,
                                         @RequestPart("订单照片") MultipartFile file,
                                         @RequestParam(value ="物品名称") String itemname,
                                         @RequestParam(value="起始地点") String start_location,
                                         @RequestParam(value="目的地") String end_location,
                                         @RequestParam(value="价格") double price,
                                         @RequestParam(value="电话号码") String phonenum)
    {   String fileName=file.getOriginalFilename();
        String filePath=path+"/"+fileName;
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Order order = new Order();
        order.setItemName(itemname);
        order.setStartLocation(start_location);
        order.setEndLocation(end_location);
        order.setPrice(BigDecimal.valueOf(price));
        order.setPhoneNumber(phonenum);
        order.setItemImageUrl(filePath);

        User user;
        user= userRepository.findById(userId).get();

        order.setUser(user);
        orderRepository.save(order);

        return "Add New Order";
    }

    @PostMapping(path="/all")
    public @ResponseBody Iterable<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @PostMapping(path="myorder")
    public @ResponseBody List<Order> getMyOrders(int userid){
        return orderRepository.findByuser_id(userid);
    }

    @PostMapping(path="test")
    public @ResponseBody String test(){
        orderRepository.save(new Order());

        return "success test";
    }

    @PostMapping(path="upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String upload(@RequestPart("file") MultipartFile file){
        return "success upload";
    }


}
