package org.example.spring;

import org.example.spring.api.SampleEntity;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 *
 *    91 | Spring BeanDefinition合并阶段：BeanDefinition合并过程是怎样出现的？
 *     {@link BeanDefinitionBuilder}
 *       {@link RootBeanDefinition}
 *       {@link GenericBeanDefinition}
 *       {@link ChildBeanDefinition}
 *       {@link AnnotatedBeanDefinition}
 *
 *    92 | Spring Bean Class加载阶段：Bean ClassLoader能够被替换吗?
 *
 *       {@link AbstractBeanFactory#getBean(java.lang.String, java.lang.Class, java.lang.Object...)}
 *           {@code AbstractAutowireCapableBeanFactory#createBean(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])}
 *              {@code AbstractBeanFactory#resolveBeanClass(org.springframework.beans.factory.support.RootBeanDefinition, java.lang.String, java.lang.Class[])}
 *                 {@code AbstractBeanFactory#doResolveBeanClass(org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Class[])}
 *
 * @author zhengshijun
 * @version created on 2020/11/4.
 */

public class MergedBeanDefinitionSample {

	public static void main(String[] args) {


		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		beanDefinitionReader.loadBeanDefinitions(new ClassPathResource("META-INF/beans.xml"));

		System.out.println(beanFactory.getBeanDefinitionCount());

		// beanFactory.getBean(SampleEntity.class);
		//
		System.out.println(beanFactory.getBean("sampleEntity",SampleEntity.class));;

		System.out.println(beanFactory.getBean("superEntity",SampleEntity.class));;
	}
}
