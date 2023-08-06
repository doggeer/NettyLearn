package rpc.network.future;

import rpc.network.msg.Response;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SyncWriteFuture implements WriteFuture<Response> {
    @Override
    public Throwable cause() {
        return null;
    }

    @Override
    public void setCause(Throwable cause) {

    }

    @Override
    public boolean isWriteSuccess() {
        return false;
    }

    @Override
    public void setWriteResult(boolean result) {

    }

    @Override
    public String requestId() {
        return null;
    }

    @Override
    public Response response() {
        return null;
    }

    @Override
    public void setResponse(Response response) {

    }

    @Override
    public boolean isTimeout() {
        return false;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Response get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Response get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
