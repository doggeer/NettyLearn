package netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import netty.codec.ObjectDecoder;
import netty.codec.ObjectEncoder;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：本客户端链接到服务端。channelId：" + channel.id());
        System.out.println("链接报告完毕");

        // 使用自定义的PB编码器进行编解码
        channel.pipeline().addLast(new ObjectDecoder());

        channel.pipeline().addLast(new ObjectEncoder());

        channel.pipeline().addLast(new MyClientHandler());
    }
}
