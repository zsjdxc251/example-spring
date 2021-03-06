package org.example.spring;

import org.example.spring.api.SampleEntity;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.InstantiationStrategy;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.Bean;

/**
 *     93 | Spring Bean实例化前阶段：Bean的实例化能否被绕开？
 *
 *        {@link InstantiationAwareBeanPostProcessor}
 *           {@link InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation(java.lang.Class, java.lang.String)}
 *           应用  {@code org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInstantiation(java.lang.Class, java.lang.String)}
 *
 *
 *
 *     94 | Spring Bean实例化阶段：Bean实例是通过Java反射创建吗？
 *         实例化方式
 *           1. 传统实例化方式
 *              实例化策略 {@link InstantiationStrategy}
 *
 *           2. 构造器依赖注入
 *
 *     95 | Spring Bean实例化后阶段：Bean实例化后是否一定被是使用吗？
 *         Bean 属性赋值 (populate)判断 false | true
 *          {@link InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation(java.lang.Object, java.lang.String)}
 *
 *
 *     96 | Spring Bean属性赋值前阶段：配置后的PropertyValues还有机会修改吗？
 *        {@link InstantiationAwareBeanPostProcessor#postProcessProperties(org.springframework.beans.PropertyValues, java.lang.Object, java.lang.String)}
 *        {@link MutablePropertyValues}
 *           {@link PropertyValue}
 *              {@code org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyPropertyValues(java.lang.String, org.springframework.beans.factory.config.BeanDefinition, org.springframework.beans.BeanWrapper, org.springframework.beans.PropertyValues)}
 *
 *
 *
 *
 *     97 | Aware接口回调阶段：众多Aware接口回调的顺序是安排的？
 *        {@link Aware}
 *           {@link BeanNameAware}   -->> {@code org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#invokeAwareMethods(java.lang.String, java.lang.Object)}
 *            ...
 *           {@link EnvironmentAware}  -->>  {@code org.springframework.context.support.ApplicationContextAwareProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)}
 *
 *        {@code org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])}
 *           {@code org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#initializeBean(java.lang.String, java.lang.Object, org.springframework.beans.factory.support.RootBeanDefinition)}
 *
 *        {@code org.springframework.context.support.ApplicationContextAwareProcessor}
 *
 * @author zhengshijun
 * @version created on 2020/11/8.
 */
public class BeanInstantiationLifecycleSample {

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

		@Override
		public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
			return false;
		}

		@Override
		public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
			return null;
		}
	}
}
