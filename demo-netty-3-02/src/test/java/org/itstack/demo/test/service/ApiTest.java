package org.itstack.demo.test.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApiTest {

    public static void main(String[] args) {
        String[] configs = {"itstack-rpc-consumer.xml", "itstack-rpc-provider.xml"};
        new ClassPathXmlApplicationContext(configs);
    }
}
