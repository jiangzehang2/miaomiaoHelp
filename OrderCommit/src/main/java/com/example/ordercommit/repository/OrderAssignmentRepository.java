package com.example.ordercommit.repository;


import com.example.ordercommit.Entity.OrderAssignment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderAssignmentRepository extends CrudRepository<OrderAssignment,Integer>{

}
