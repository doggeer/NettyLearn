package netty.server;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * ChannelGroup组管理器
 * @author wc
 */
public class ChannelHandler {

    private static ChannelGroup defaultChannelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 获取默认的管道组
     * @return  channelGroup
     */
    public static ChannelGroup channelGroup() {
        return defaultChannelGroup;
    }
}
