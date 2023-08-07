package rpc.reflect;

import cn.hutool.core.util.ClassLoaderUtil;
import rpc.network.msg.Request;

import java.lang.reflect.Proxy;

public class JDKProxy {

    public static <T> T getProxy(Class<T> interfaceClass, Request request) {
        JDKInvocationHandler handler = new JDKInvocationHandler(request);
        ClassLoader classLoader = ClassLoaderUtil.getContextClassLoader();

        T result = (T) Proxy.newProxyInstance(classLoader, new Class[]{interfaceClass}, handler);
        return result;
    }


}
