package com.example.ordercommit;

import com.example.ordercommit.repository.OrderAssignmentRepository;
import com.example.ordercommit.repository.OrderRepository;
import com.example.ordercommit.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderCommitApplicationTests {

    @Autowired
    OrderAssignmentRepository orderAssignmentRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Test
    void contextLoads() {

    }

}
