package netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

/**
 * @author wc
 * 自定义编码器
 * 编码规则:02开始,03结束.
 */
public class MyEncoder extends MessageToByteEncoder {


    @Override
    protected void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
        // 编码规则: 02开始,03结束,编码采用GBK进行编码

        String msg = in.toString();

        byte[] msgByteArray = msg.getBytes(Charset.forName("GBK"));


        // 创建一个消息内容长度+2的byte数组,用来存放即将发送的消息.
        byte[] sendMsg = new byte[msgByteArray.length + 2];

        System.arraycopy(msgByteArray, 0, sendMsg, 1, msgByteArray.length);

        // 在数组的首位添加02,03
        sendMsg[0] = 0x02;
        sendMsg[sendMsg.length - 1] = 0x03;

        out.writeInt(sendMsg.length);
        out.writeBytes(sendMsg);
    }
}
