package rpc.config.spring.beans;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.FactoryBean;
import rpc.config.ConsumerConfig;
import rpc.config.ServerConfig;
import rpc.network.client.ClientSocket;
import rpc.network.msg.Request;

public class ConsumerBean<T> extends ConsumerConfig implements FactoryBean {
    private ChannelFuture channelFuture;

    private ServerConfig serverConfig;



    @Override
    public Object getObject() throws Exception {
        if (null == serverConfig) {
            String infoStr = RedisRegistryCenter.obtainProvider(nozzle, alias);
            serverConfig = JSON.parseObject(infoStr, ServerConfig.class);
        }

        Assert.isTrue(serverConfig != null);


        if (null == channelFuture) {
            ClientSocket clientSocket = new ClientSocket(serverConfig.getHost(), Integer.parseInt(serverConfig.getPort()));
            new Thread(clientSocket).run();

            for (int i = 0; i < 100; i++) {

                if (null != channelFuture) {
                    break;
                }

                Thread.sleep(500L);
                channelFuture = clientSocket.getFuture();
            }
        }


        Assert.isTrue(channelFuture != null);

        Request request = new Request();

        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }


    @Override
    public boolean isSingleton() {
        return true;
    }
}
