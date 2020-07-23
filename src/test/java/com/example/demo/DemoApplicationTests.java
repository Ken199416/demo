package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        String s = "this is    a           book";
        String [] array = s.split("");
        for (String ss:array
             ) {
            System.out.println(ss);
        }
    }


    @Test
    public void test001(){
        System.out.println(System.getProperty("user.dir"));
    }

}
