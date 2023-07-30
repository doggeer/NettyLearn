package rpc.config.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import rpc.config.spring.beans.ConsumerBean;
import rpc.config.spring.beans.ProviderBean;
import rpc.config.spring.beans.ServiceBean;

public class MyNameSpaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("consumer", new MyBeanDefinitionParser(ConsumerBean.class));
        registerBeanDefinitionParser("provider", new MyBeanDefinitionParser(ProviderBean.class));
        registerBeanDefinitionParser("server", new MyBeanDefinitionParser(ServiceBean.class));
    }
}
