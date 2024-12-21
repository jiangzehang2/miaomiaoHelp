package com.example.ordercommit.repository;

import com.example.ordercommit.Entity.Order;
import com.example.ordercommit.Entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository  extends CrudRepository<Post, Integer> {
    @Override
    List<Post> findAll();


}
