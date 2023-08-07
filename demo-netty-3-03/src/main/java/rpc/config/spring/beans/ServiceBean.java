package rpc.config.spring.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import rpc.config.ServerConfig;
import rpc.config.registry.RedisRegistryCenter;
import rpc.network.config.LocalServerInfo;
import rpc.network.server.ServerSocket;

public class ServiceBean extends ServerConfig implements ApplicationContextAware {

    private static Logger log = LoggerFactory.getLogger(ProviderBean.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 进行生产者信息发布
        log.info("启动注册中心....");
        RedisRegistryCenter.init(getHost(), getPort());
        log.info("启动注册中心完成 {} {}", getHost(), getPort());

        // 初始化服务端
        ServerSocket serverSocket = new ServerSocket(applicationContext);
        new Thread(serverSocket).start();


        log.info("初始化生产端服务完成 {} {}", LocalServerInfo.LOCAL_HOST, LocalServerInfo.LOCAL_PORT);
    }
}
