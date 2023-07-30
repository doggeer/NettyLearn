package rpc.config.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 自定义BeanDefinition的解析器,进行对象容器解析,然后将对象注册到Spring的BeanDefinitionMap中,交给Spring进行初始化
 * @author wc
 */
public class MyBeanDefinitionParser implements BeanDefinitionParser {
    private final Class<?> beanClass;

    public MyBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        // 解析配置文件,创建BeanDefinition进行搭建
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);

        String beanName = element.getAttribute("id");
        parserContext.getRegistry().registerBeanDefinition(beanName, beanDefinition);


        for (Method method : beanClass.getMethods()) {

            // 如果不是属性的方法,进行跳过
            if (!isProperty(method, beanClass)) {
                continue;
            }

            String name = method.getName();
            String attributeName = name.substring(3, 4).toLowerCase() + name.substring(4);

            String attributeValue = element.getAttribute(attributeName);
            beanDefinition.getPropertyValues().addPropertyValue(attributeName, attributeValue);
        }

        return beanDefinition;
    }


    /**
     * 判断传入的方法是不是set 成员变量方法,并且有对应的get方法.
     * @param method
     * @param beanClass
     * @return
     */
    private boolean isProperty(Method method, Class beanClass) {

        String methodName = method.getName();
        boolean flag = methodName.length() > 3 && methodName.startsWith("set") && Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 1;
        Method getter = null;
        if (!flag) return false;  // 不是set方法,返回false

        Class<?> type = method.getParameterTypes()[0];
        try {
            getter = beanClass.getMethod("get" + methodName.substring(3));
        } catch (NoSuchMethodException ignore) {

        }

        if (null == getter) {
            try {
                getter = beanClass.getMethod("is" + methodName.substring(3));
            } catch (NoSuchMethodException ignore) {

            }
        }

        flag = getter != null && Modifier.isPublic(getter.getModifiers()) && type.equals(getter.getReturnType());

        return flag;

    }
}
