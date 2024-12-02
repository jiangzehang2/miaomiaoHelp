package com.example.bookmanage;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    protected Customer() {}

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;

    }
    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d,name='%s']",id,name
        );
    }

    public Long getId() {
        return id;
    }

    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
}


