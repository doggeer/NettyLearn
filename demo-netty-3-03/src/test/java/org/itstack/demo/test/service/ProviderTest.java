package org.itstack.demo.test.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderTest {

    public static void main(String[] args) {
        String[] configs = {"itstack-rpc-provider.xml"};
        new ClassPathXmlApplicationContext(configs);
        System.out.println("哈哈哈");
    }
}
