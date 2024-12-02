package com.example.bookmanage;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {
    
    OrderLine findById(long id);
}
