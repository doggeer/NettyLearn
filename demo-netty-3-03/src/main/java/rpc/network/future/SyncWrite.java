package rpc.network.future;

import io.netty.channel.Channel;
import rpc.network.msg.Request;
import rpc.network.msg.Response;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SyncWrite {

    public Response writeAndSync(final Channel channel, final Request request, final long timeout) throws Exception {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        if (request == null) {
            throw new NullPointerException("request");
        }

        if (timeout <= 0) {
            throw new IllegalArgumentException("timeout < 0");
        }

        String requestId = UUID.randomUUID().toString();
        request.setRequestId(requestId);


        SyncWriteFuture syncWriteFuture = new SyncWriteFuture(request.getRequestId());// 创建一个异步写入的线程.

        SyncWriteMap.put(requestId, syncWriteFuture);


        Response response = doWriteAndSync(channel, request, timeout, syncWriteFuture);

        return response;
    }

    private Response doWriteAndSync(Channel channel, Request request, long timeout, SyncWriteFuture writeFuture) throws Exception {

        channel.writeAndFlush(request).addListener((future -> {

            writeFuture.setWriteResult(future.isSuccess());
            writeFuture.setCause(future.cause());

            if (!writeFuture.isWriteSuccess()) {
                SyncWriteMap.syncKey.remove(writeFuture.requestId());
            }
        }));

        Response response = writeFuture.get(timeout, TimeUnit.MILLISECONDS);

        if (response == null) {
            if (writeFuture.isTimeout()) {
                throw new TimeoutException();
            } else {
                throw new Exception(writeFuture.cause());
            }

        }

        return response;
    }
}
