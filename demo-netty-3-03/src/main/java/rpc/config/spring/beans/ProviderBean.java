package rpc.config.spring.beans;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import rpc.config.ProviderConfig;
import rpc.config.registry.RedisRegistryCenter;
import rpc.network.config.LocalServerInfo;

/**
 * 服务提供方的配置Bean
 * @author wc
 */

public class ProviderBean extends ProviderConfig implements ApplicationContextAware {

    private static Logger log = LoggerFactory.getLogger(ProviderBean.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setNozzle(getNozzle());
        providerConfig.setRef(getRef());
        providerConfig.setAlias(getAlias());
        providerConfig.setHost(LocalServerInfo.LOCAL_HOST);
        providerConfig.setPort(LocalServerInfo.LOCAL_PORT);
        // 在Spring容器的发布流程中进行节点发布

        RedisRegistryCenter.registerProvider(getNozzle(), getAlias(), JSON.toJSONString(providerConfig));
        log.info("注册服务提供者:{}", JSON.toJSONString(providerConfig));

    }
}
