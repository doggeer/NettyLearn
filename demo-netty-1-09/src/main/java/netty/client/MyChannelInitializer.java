package netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import netty.msghandler.MyInMsgHandler;
import netty.msghandler.MyOutMsgHandler;

import java.nio.charset.Charset;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：本客户端链接到服务端。channelId：" + channel.id());
        System.out.println("链接报告完毕");

        // 增加自定义处理器,处理粘包,半包
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));

        // 添加字符串解码器
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));

        //添加字符串编码器
        channel.pipeline().addLast((new StringEncoder(Charset.forName("GBK"))));

        // 需要在pipline中先添加out再添加in。不然out的write方法不会执行
        // 先添加自定义out处理器.
        channel.pipeline().addLast(new MyOutMsgHandler());

        // 在提那家自定义in处理器.
        channel.pipeline().addLast(new MyInMsgHandler());


    }
}
