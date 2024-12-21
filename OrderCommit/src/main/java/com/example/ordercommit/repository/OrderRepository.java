package com.example.ordercommit.repository;

import com.example.ordercommit.Entity.Order;
import com.example.ordercommit.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findByUser(User user);


    List<Order> findByStatus(String status);
}
