package rpc.network.future;

import io.netty.channel.Channel;
import rpc.network.msg.Request;
import rpc.network.msg.Response;

import javax.validation.constraints.Null;
import java.util.UUID;

public class SyncWrite {

    public Response writeAndSync(final Channel channel, final Request request, final long timeOut) {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        if (request == null) {
            throw new NullPointerException("request");
        }

        if (timeOut <= 0) {
            throw new IllegalArgumentException("time_out < 0");
        }

        String requestId = UUID.randomUUID().toString();
        request.setRequestId(requestId);


        new SyncWriteFuture(request.getRequestId()); // 创建一个异步写入的线程.

    }
}
