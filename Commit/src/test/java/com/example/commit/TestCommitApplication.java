package com.example.commit;

import org.springframework.boot.SpringApplication;

public class TestCommitApplication {

    public static void main(String[] args) {
        SpringApplication.from(CommitApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
