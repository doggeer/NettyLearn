package rpc.reflect;

import rpc.network.future.SyncWrite;
import rpc.network.msg.Request;
import rpc.network.msg.Response;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKInvocationHandler implements InvocationHandler {

    private Request request;

    public JDKInvocationHandler(Request request) {
        this.request = request;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();

        if ("toString".equals(methodName) && parameterTypes.length == 0) {
            return request.toString();
        }
        if ("hashCode".equals(methodName) && parameterTypes.length == 0) {
            return request.hashCode();
        }

        if ("equals".equals(methodName) && parameterTypes.length == 1) {
            return request.equals(args[0]);
        }


        // 设置参数
        request.setMethodName(methodName);
        request.setParamTypes(parameterTypes);
        request.setArgs(args);
        request.setRef(request.getRef());

        // 创建一个异步调用
        Response response = new SyncWrite().writeAndSync(request.getChannel(), request, 5000);
        return response.getResult();
    }
}
