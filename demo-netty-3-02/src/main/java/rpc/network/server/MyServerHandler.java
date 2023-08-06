package rpc.network.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import rpc.network.msg.Request;
import rpc.network.msg.Response;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Request request = (Request) msg;


        Response response = new Response();
        response.setRequestId(request.getRequestId());
        response.setParam(request.getResult()+"请求成功,反馈结果");
        ctx.channel().writeAndFlush(response);

        ReferenceCountUtil.release(msg);
    }
}
