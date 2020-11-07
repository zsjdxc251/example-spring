package org.example.spring;

import org.example.spring.api.SampleEntity;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengshijun
 * @version created on 2020/11/7.
 */
@Configuration
public class BeanInitializationLifecycleSample {

	@Bean
	public SampleEntity sampleEntity() {
		return new SampleEntity();
	}

	public static void main(String[] args) {

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		beanFactory.addBeanPostProcessor(new CustomInstantiationAwareBeanPostProcessor());
		AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
		int count = beanFactory.getBeanDefinitionCount();
		beanDefinitionReader.registerBean(SampleEntity.class,()->{
			SampleEntity sampleEntity = new SampleEntity();
			sampleEntity.setId(System.nanoTime());
			sampleEntity.setName(Thread.currentThread().getName());
			return sampleEntity;
		});

		System.out.println("加载了多少bean:"+(beanFactory.getBeanDefinitionCount()-count));

		System.out.println(beanFactory.getBean(SampleEntity.class));
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("sampleEntity");

	}


	static class CustomInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
			// 替换类
			return new SampleEntity();
		}
	}


}
