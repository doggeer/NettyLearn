package netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by wc on 2020/5/13.
 * netty server
 * nettyServer端
 * @version 1.0
 * @author wc
 */
public class NettyServer {

    public static void main(String[] args) {
        NettyServer server = new NettyServer();
        server.bind("120.0.0.1", 7090);
    }


    /**
     * 监听服务器IP和端口
     * @param ip    服务器IP
     * @param port  服务器端口
     */
    private void bind(String ip, int port) {

        NioEventLoopGroup parentGroup = new NioEventLoopGroup();
        NioEventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new MyChannelInitializer());

            ChannelFuture channelFuture = b.bind(port).sync(); // 异步监听端口,等待请求
            channelFuture.channel().closeFuture().sync();
            System.out.println("服务器启动成功,正在等待链接....");
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }

    }
}
