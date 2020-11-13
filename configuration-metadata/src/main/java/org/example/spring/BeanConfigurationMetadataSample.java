package org.example.spring;

import org.example.spring.api.SampleEntity;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 *
 *       108 | Spring Bean属性元信息：PropertyValues
 *
 *
 *
 *       109 | Spring容器配置元信息
 *       {@link BeanDefinitionParserDelegate}
 *
 * @author zhengshijun
 * @version created on 2020/11/13.
 */
public class BeanConfigurationMetadataSample {

	public static void main(String[] args) {

		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SampleEntity.class);
		builder.addPropertyValue("name", "zsj");

		AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
		beanDefinition.setAttribute("name", "时均");
		beanDefinition.setSource(SampleEntity.class);


		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
				if (ObjectUtils.nullSafeEquals(beanName, "sampleEntity")) {
					BeanDefinition beanInfo = beanFactory.getBeanDefinition(beanName);
					if (Objects.equals(beanInfo.getSource(), SampleEntity.class)) {
						SampleEntity.class.cast(bean).setName(String.valueOf(beanInfo.getAttribute("name")));
					}
				}
				return bean;
			}
		});

		beanFactory.registerBeanDefinition("sampleEntity", beanDefinition);

		System.out.println(beanFactory.getBean("sampleEntity"));
	}
}
