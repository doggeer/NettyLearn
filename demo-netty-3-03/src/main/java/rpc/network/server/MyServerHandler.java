package rpc.network.server;

import cn.hutool.core.util.ClassLoaderUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.springframework.context.ApplicationContext;
import rpc.network.msg.Request;
import rpc.network.msg.Response;

import java.lang.reflect.Method;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    private ApplicationContext applicationContext;

    public MyServerHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Request request = (Request) msg;
        Response response = new Response();
        response.setRequestId(request.getRequestId());

        // 根据请求的内容进行反射调用.
        Class<?> classType = ClassLoaderUtil.loadClass(request.getNozzle());
        Method addMethod = classType.getMethod(request.getMethodName(), request.getParamTypes());
        Object targetBean = applicationContext.getBean(request.getRef());

        Object result = addMethod.invoke(targetBean, request.getArgs());

        response.setResult(result);
        response.setParam(request.getResult()+"请求成功,反馈结果");
        ctx.channel().writeAndFlush(response);

        // 释放当前这条消息.
        ReferenceCountUtil.release(msg);
    }
}
