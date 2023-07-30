package netty.client;

import com.google.protobuf.util.JsonFormat;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.domain.MsgBody;
import netty.util.MsgUtil;

import java.util.Date;

public class MyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 通道激活
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        String msgInfo = "客户端发来通知,通道已经建立";

        MsgBody msgBody = MsgUtil.buildMsg(ctx.channel().toString(), msgInfo);
        ctx.writeAndFlush(msgBody);
    }

    /**
     * 通道断开链接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    /**
     * 接收到通道的消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new Date() + "接收到消息:");
        System.out.println("客户端接收到消息:"+ JsonFormat.printer().print((MsgBody)msg));
    }
}
