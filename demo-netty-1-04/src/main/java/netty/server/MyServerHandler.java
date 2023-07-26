package netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 进行消息输出
        System.out.println(new Date() + "接收到消息:");
        System.out.println(msg);
    }

    // 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String responese = "connect established...";

        // 通过二进制数据进行数据发送

        ByteBuf buf = Unpooled.buffer(responese.getBytes().length); // 创建一个数据Buf
        buf.writeBytes(responese.getBytes("GBK")); // 按照数编码据格式写入数据
        ctx.channel().writeAndFlush(buf); // 发送数据.

    }

    /**
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    //    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        // 自己进行二进制转码
//
//        // 接收msg消息
//        ByteBuf buf = (ByteBuf) msg;
//
//        byte[] msgByte = new byte[buf.readableBytes()]; // 获取数据的字节长度,创建字节数组
//        buf.readBytes(msgByte);
//
//        // 进行消息输出
//        System.out.println(new Date() + "接收到消息:");
//
//
//        System.out.println(new String(msgByte, Charset.forName("GBK")));
//    }
}
