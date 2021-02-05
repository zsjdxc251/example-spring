package org.example.spring;

import java.util.List;
import org.example.spring.model.BaseProcess;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 *  {@link DefaultListableBeanFactory#doResolveDependency(org.springframework.beans.factory.config.DependencyDescriptor, java.lang.String, java.util.Set, org.springframework.beans.TypeConverter)}
 * @author zhengshijun
 * @date 2021/1/22.
 */
@ComponentScan("org.example.spring.model")
public class ListDependencyInjectionSample implements InitializingBean {

    @Autowired
    private List<BaseProcess> list;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ListDependencyInjectionSample.class);

        applicationContext.start();

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(list);
    }
}
