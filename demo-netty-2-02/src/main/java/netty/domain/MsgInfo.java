package netty.domain;

public class MsgInfo {

    /**
     * 消息内容
     */
    private String msg;

    /**
     * channelId
     */
    private String channelId;

    public MsgInfo(String msg, String channelId) {
        this.msg = msg;
        this.channelId = channelId;
    }

    public MsgInfo() {
    }

    @Override
    public String toString() {
        return "MsgInfo{" +
                "msg='" + msg + '\'' +
                ", channelId='" + channelId + '\'' +
                '}';
    }
}
