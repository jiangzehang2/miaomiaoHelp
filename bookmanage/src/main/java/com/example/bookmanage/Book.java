package com.example.bookmanage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String Info;

    protected Book() {}

    public Book(String name, String info) {
        this.name = name;
        Info = info;
    }
    @Override
    public String toString() {
        return String.format("Book [id=%s, name=%s, Info=%s]", id, name, Info);
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getInfo() {
        return Info;
    }
}
