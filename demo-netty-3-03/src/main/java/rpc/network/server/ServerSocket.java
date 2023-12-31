package rpc.network.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.context.ApplicationContext;
import rpc.network.codec.RpcDecoder;
import rpc.network.codec.RpcEncoder;
import rpc.network.msg.Request;
import rpc.network.msg.Response;

public class ServerSocket implements Runnable{
    private ApplicationContext applicationContext;

    private String inet;

    private int port;

    public ServerSocket(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ServerSocket(ApplicationContext applicationContext, String inet, int port) {
        this.applicationContext = applicationContext;
        this.inet = inet;
        this.port = port;
    }

    @Override
    public void run() {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch){
                            ch.pipeline().addLast(
                                    new RpcDecoder(Request.class),
                                    new RpcEncoder(Response.class),
                                    new MyServerHandler(applicationContext));
                        }
                    });

            ChannelFuture f = null;
            f = b.bind(inet,port).sync();
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }
}
