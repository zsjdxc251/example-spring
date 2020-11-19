package org.example.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 *
 *
 * @author zhengshijun
 * @version created on 2020/11/19.
 */
public class XmlYamlPropertySourceSample {

	public static void main(String[] args) {

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		beanDefinitionReader.loadBeanDefinitions("META-INF/property_source_bean.xml");
		// {user={name=zsj}}
		System.out.println(beanFactory.getBean("yamlMap"));
	}
}
