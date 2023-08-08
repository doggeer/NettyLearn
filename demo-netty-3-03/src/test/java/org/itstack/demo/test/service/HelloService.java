package org.itstack.demo.test.service;

public class HelloService implements HelloServiceInterface {
    public HelloService() {
        System.out.println("HelloService被注册....");
    }

    public String getName(){
        return "王超";
    }
}
