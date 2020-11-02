package org.example.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;


/**
 *
 *    89 | Spring Bean 元信息解析阶段：BeanDefinition的解析
 *
 *
 *
 *    90 | Spring Bean 注册阶段：BeanDefinition与单体Bean注册
 *
 *    {@link DefaultListableBeanFactory#registerBeanDefinition(java.lang.String, org.springframework.beans.factory.config.BeanDefinition)}
 *
 *
 * @author zhengshijun
 * @version created on 2020/11/2.
 */
public class AnnotatedBeanDefinitionParsingSample {

	public static void main(String[] args) {


		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);


		beanDefinitionReader.register(AnnotatedBeanDefinitionParsingSample.class);

		int count = beanFactory.getBeanDefinitionCount();
		System.out.println(count);
		System.out.println(String.join("\n",beanFactory.getBeanDefinitionNames()));
	}
}
