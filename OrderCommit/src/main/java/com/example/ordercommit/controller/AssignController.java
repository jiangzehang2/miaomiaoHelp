package com.example.ordercommit.controller;


import com.example.ordercommit.Order;
import com.example.ordercommit.OrderAssignment;
import com.example.ordercommit.User;
import com.example.ordercommit.repository.OrderAssignmentRepository;
import com.example.ordercommit.repository.OrderRepository;
import com.example.ordercommit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/Assign")
public class AssignController {

    OrderRepository orderRepository;
    UserRepository userRepository;
    @Autowired
    OrderAssignmentRepository orderAssignmentRepository;
    @PostMapping(path="/accept")
    public @ResponseBody String accept(@RequestParam int orderid,
                                       @RequestParam int runnerid
                                                                )
    {
       Order order=orderRepository.findById(orderid).get();
       order.setStatus("pending");
        orderRepository.save(order);

        User user=userRepository.findById(runnerid).get();

        OrderAssignment orderAssignment=new OrderAssignment();
        orderAssignment.setOrder(order);
        orderAssignment.setStatus("accepted");
        orderAssignment.setRunner(user);
        orderAssignmentRepository.save(orderAssignment);

        return "success";
    }
    @PostMapping(path="/complete")
    public @ResponseBody String cpmplete(int orderassignid){
        OrderAssignment orderAssignment=orderAssignmentRepository.findById(orderassignid).get();
        orderAssignment.setStatus("completed");
        orderAssignmentRepository.save(orderAssignment);

        Order order=orderAssignment.getOrder();
        order.setStatus("completed");
        orderRepository.save(order);
        return "success";

    }
}
