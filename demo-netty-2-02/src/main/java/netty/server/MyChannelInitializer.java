package netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import netty.codec.ObjectDecoder;
import netty.codec.ObjectEncoder;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {

        // 管道处理器进行初始化
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：有一客户端链接到本服务端");
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接报告Port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");

        // 使用自定义的PB编码器进行编解码
        channel.pipeline().addLast(new ObjectDecoder());
        channel.pipeline().addLast(new ObjectEncoder());

        // 在消息处理管道中添加我们自己的处理器
        channel.pipeline().addLast(new MyServerHandler());
    }
}
