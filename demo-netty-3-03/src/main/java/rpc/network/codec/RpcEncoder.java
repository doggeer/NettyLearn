package rpc.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import rpc.network.util.SerializationUtil;

public class  RpcEncoder extends MessageToByteEncoder {

    private Class<?> clazz;

    public RpcEncoder(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        // 将我们传入的对象,通过protostuff序列化成二进制byte[]
        if (clazz.isInstance(msg)) {
            byte[] bytes = SerializationUtil.serialObject(msg);
            out.writeInt(bytes.length);
            out.writeBytes(bytes);
        }
    }
}
