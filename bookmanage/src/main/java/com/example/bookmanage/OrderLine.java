package com.example.bookmanage;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long price;
    private Long discount;

    protected OrderLine() {}

    public OrderLine(Long price, Long discount) {
        this.price = price;
        this.discount = discount;
    }
    @Override
    public String toString() {
        return String.format(
                "OrderLine[ price=%d, discount=%d]", price, discount
        );
    }

    public Long getPrice() {
        return price;
    }
    public Long getDiscount() {
        return discount;
    }
}
