package netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import netty.domain.MsgInfo;
import netty.util.SerializationUtil;

import java.util.List;

public class ObjectDecoder extends ByteToMessageDecoder {

    private Class<?> genericClass;



    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }

        in.markReaderIndex();
        int dataLength = in.readInt();

        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }

        byte[] data = new byte[dataLength];

        in.readBytes(data);

        // 使用序列化工具,将二进制数据反序列化成Java对象
        out.add(SerializationUtil.deSerialObject(MsgInfo.class, data));
    }
}
