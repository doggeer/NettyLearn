package org.itstack.demo.test.service;

import rpc.network.server.ServerSocket;

public class StartServer {

    public static void main(String[] args) {
        System.out.println("启动服务端开始");
        new Thread(new ServerSocket()).start();
        System.out.println("启动服务端完成");
    }
}
