package org.itstack.demo.test.service;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelFuture;
import rpc.network.client.ClientSocket;
import rpc.network.future.SyncWrite;
import rpc.network.msg.Request;
import rpc.network.msg.Response;

public class StartClient {
    private static ChannelFuture future;


    public static void main(String[] args) throws Exception {
        ClientSocket clientSocket = new ClientSocket();
        new Thread(clientSocket).start();


        while (true) {

            try {
                if (null == future) {
                    future = clientSocket.getFuture();
                    Thread.sleep(500);
                    continue;
                }

                Request request = new Request();
                request.setResult("查询用户信息");
                SyncWrite syncWrite = new SyncWrite();

                Response response = syncWrite.writeAndSync(future.channel(), request, 1000);
                System.out.println("调用结果：" + JSON.toJSON(response));
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


}
