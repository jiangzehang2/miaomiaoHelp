package com.example.bookmanage;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BookmanageApplication {
    private static final Logger log=LoggerFactory.getLogger(BookmanageApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BookmanageApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository,BookRepository bookRepository,OrderLineRepository orderLineRepository){
        return (args)->{
            Scanner sc=new Scanner(System.in);
            while(true) {
                System.out.println("Please choose to register or log in");
                String n1=sc.nextLine();
                AtomicBoolean flag= new AtomicBoolean(false);
                if(n1.equals("register")) {
                    System.out.println("Please enter your name and password");
                     String n2=sc.next();
                    String n3=sc.next();
                    repository.save(new Customer(n2,n3));
                }
                if(n1.equals("login")) {
                    System.out.println("Please enter your name and password");
                    n1=sc.next();
                    String n2=sc.next();
                    repository.findByName(n1).forEach(
                            n->{
                                if(n.getPassword().equals(n2)) {
                                    flag.set(true);}
                            }
                    );
                    if(!flag.get()){
                        System.out.println("Error!");
                    }

                }
                if(flag.get()) {
                    break;
                }


            }
            Order order = null;
            while(true){
                System.out.println("Please enter the book you want to buy");
                System.out.println("if you want to exit,enter exit");
                System.out.println("if you have chosen all books that you need to buy,please enter over");
                String n1=sc.next();
                if(n1.equals("exit")) { return;}
                if(n1.equals("over")) { break;}
                Book n2=  bookRepository.findByName(n1);
                Long n3=n2.getId();
                OrderLine o1=orderLineRepository.findById(n3).get();
                order.addNew(o1);
            }
            order.toString();

        };
    }

}
