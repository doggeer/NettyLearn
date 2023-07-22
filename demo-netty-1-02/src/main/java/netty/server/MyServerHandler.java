package netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        // 接收msg消息
        ByteBuf buf = (ByteBuf) msg;

        byte[] msgByte = new byte[buf.readableBytes()]; // 获取数据的字节长度,创建字节数组
        buf.readBytes(msgByte);

        // 进行消息输出
        System.out.println(new Date() + "接收到消息:");
        System.out.println(new String(msgByte, Charset.forName("GBK")));
    }
}
