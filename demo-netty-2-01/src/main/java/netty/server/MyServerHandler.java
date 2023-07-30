package netty.server;

import com.google.protobuf.util.JsonFormat;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.domain.MsgBody;
import netty.util.MsgUtil;

import java.util.Date;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    // 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        String msgInfo = "服务端发来通知:通道已经创建";

        MsgBody msgBody = MsgUtil.buildMsg(ctx.channel().toString(), msgInfo);


        ctx.writeAndFlush(msgBody);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 进行消息输出
        System.out.println(new Date() + "接收到消息:");
        System.out.println(JsonFormat.printer().print((MsgBody)msg));
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
}
