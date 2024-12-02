package com.example.commit;

import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/demo")
public class MainController {
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(path="/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping(path="/add")
    public @ResponseBody String addNewOrder(@RequestBody Order order ) {
    orderRepository.save(order);
    return "Add Success!";
    }

    @GetMapping(path = "/showall")
    public @ResponseBody Iterable<Order> showAllOrders() {
        return orderRepository.findAll();
    }
}
