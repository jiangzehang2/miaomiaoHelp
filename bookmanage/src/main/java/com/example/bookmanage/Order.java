package com.example.bookmanage;

import java.util.List;
import java.util.Optional;

public class Order {
    private Long total;
    List<OrderLine> line;

    private Order(){
        total = 0L;
    }

    public Long getTotal() {
        return total;
    }
    public void addNew(OrderLine orderLine){
        total=orderLine.getPrice()+total;
        line.add(orderLine);
    }
    @Override
    public String toString() {
        return "Order [total=" + total + ", line=" + line + "]";
    }


}
