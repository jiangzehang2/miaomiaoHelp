package com.example.bookmanage;

import java.util.List;
import org.springframework.data.repository.CrudRepository;



public interface BookRepository extends CrudRepository<Book,Long> {
    Book findByName(String Name);
    Book findById(long id);

}
