package netty.util;

import netty.domain.MsgInfo;

/**
 * 对传入的消息进行序列化和反序列化
 */
public class MsgUtil {

    public static MsgInfo buildMsg(String channelId, String msg) {
        MsgInfo msgInfo = new MsgInfo(msg, channelId);
        return msgInfo;
    }



}
