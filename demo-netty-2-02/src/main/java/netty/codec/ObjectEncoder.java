package netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import netty.domain.MsgInfo;
import netty.util.SerializationUtil;

public class ObjectEncoder extends MessageToByteEncoder<MsgInfo> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MsgInfo msg, ByteBuf out) throws Exception {
        byte[] data = SerializationUtil.serialObject(msg);
        out.writeInt(data.length);
        out.writeBytes(data);

    }
}
