package netty.util;

import netty.domain.MsgBody;

public class MsgUtil {

    /**
     * 创建PB传输对象
     * @param channelId
     * @param msgInfo
     * @return
     */
    public static MsgBody buildMsg(String channelId, String msgInfo) {
        MsgBody.Builder msg = MsgBody.newBuilder();
        msg.setMsgInfo(msgInfo);
        msg.setChannelId(channelId);

        return msg.build();
    }


}
