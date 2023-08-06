package rpc.network.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import rpc.network.future.SyncWriteMap;
import rpc.network.future.WriteFuture;
import rpc.network.msg.Response;

public class MyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Response response = (Response) msg;

        String requestId = response.getRequestId();
        WriteFuture writeFuture = SyncWriteMap.syncKey.get(requestId);

        if (writeFuture != null) {
            writeFuture.setResponse(response);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
