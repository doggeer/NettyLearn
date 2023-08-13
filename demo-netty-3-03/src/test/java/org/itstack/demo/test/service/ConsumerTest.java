package org.itstack.demo.test.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerTest {

    public static void main(String[] args) {
        String[] configs = {"itstack-rpc-consumer.xml"};
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext(configs);

        IUserService userService = (IUserService) application.getBean("userService");


        HelloServiceInterface helloService = (HelloServiceInterface) application.getBean("helloService");

        String name = helloService.getName();
        System.out.println(name);


        System.out.println("哈哈哈");
    }
}
