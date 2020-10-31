package org.example.spring;

import org.example.spring.api.SampleEntity;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 *
 *
 *     88 | Spring Bean 元信息配置阶段：BeanDefinition配置与扩展
 *
 *         {@link AbstractBeanDefinitionReader}
 *
 *               {@link PropertiesBeanDefinitionReader}
 *               {@link XmlBeanDefinitionReader}
 *
 * @author zhengshijun
 * @version created on 2020/10/31.
 */
public class BeanMetadataConfigurationSample {

	public static void main(String[] args) {

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);

		Resource resource = new ClassPathResource("META-INF/entity.properties");

		EncodedResource encodedResource = new EncodedResource(resource);
		int beanNumbers = reader.loadBeanDefinitions(encodedResource);

		System.out.println(beanNumbers);

		SampleEntity sampleEntity = beanFactory.getBean("user",SampleEntity.class);
		System.out.println(sampleEntity);


	}
}
