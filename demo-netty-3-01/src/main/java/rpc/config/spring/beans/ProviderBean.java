package rpc.config.spring.beans;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import rpc.config.ProviderConfig;

/**
 * 服务提供方的配置Bean
 * @author wc
 */
public class ProviderBean extends ProviderConfig implements ApplicationContextAware {
    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        // 在Spring容器的发布流程中进行节点发布
        doExport();
    }
}
