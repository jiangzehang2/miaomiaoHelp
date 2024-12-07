package com.example.ordercommit.repository;

import com.example.ordercommit.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findByuser_id(int userid);

    List<Order> findByStatus(String status);
}
