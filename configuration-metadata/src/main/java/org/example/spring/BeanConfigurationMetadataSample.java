package org.example.spring;

import org.example.spring.api.SampleEntity;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Profile;
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
 *
 *       110 | 基于XML资源装载Spring Bean配置元信息
 *       {@link XmlBeanDefinitionReader}
 *         {@link BeanDefinitionHolder}
 *
 *       111 | 基于Properties资源装载Spring Bean配置元信息：为什么Spring官方不推荐？
 *       {@link PropertiesBeanDefinitionReader}
 *
 *
 *       112 | 基于Java注解装载Spring Bean配置元信息
 *       {@link AnnotatedBeanDefinitionReader}
 *       {@link ClassPathScanningCandidateComponentProvider}
 *          {@link ClassPathScanningCandidateComponentProvider#registerDefaultFilters()}
 *
 *       {@link AutowiredAnnotationBeanPostProcessor}
 *          {@link AutowiredAnnotationBeanPostProcessor#AutowiredAnnotationBeanPostProcessor()}
 *
 *       {@link CommonAnnotationBeanPostProcessor}
 *
 *
 *       {@link Profile}
 *       -- {@link org.springframework.context.annotation.ProfileCondition}
 *          {@link org.springframework.context.annotation.ConditionEvaluator}
 *
 *
 *
 *       113 | Spring Bean配置元信息底层实现之XML资源
 *
 *        Xml 资源 {@link BeanDefinition} 解析和注册
 *            核心 ApI {@link XmlBeanDefinitionReader}
 *            资源 - {@link org.springframework.core.io.Resource}
 *            底层 - {@link org.springframework.beans.factory.xml.BeanDefinitionDocumentReader}
 *                * XML 解析 - Java Dom Level 3 Api
 *                * {@link BeanDefinition} 解析 {@link BeanDefinitionParserDelegate}
 *                * {@link BeanDefinition} 注册 {@link org.springframework.beans.factory.support.BeanDefinitionRegistry}
 *
 *        扩展
 *        {@link XmlBeanDefinitionReader#documentReaderClass} -> {@link DefaultBeanDefinitionDocumentReader}
 *
 *
 *        115 | Spring Bean配置元信息底层实现之Java注解
 *
 *        Java 注册 {@link BeanDefinition}
 *           核心 API - {@link AnnotatedBeanDefinitionReader}
 *              资源
 *              底层
 *                  * 条件 {@link org.springframework.context.annotation.ConditionEvaluator}
 *                  * Bean 范围解析 {@link org.springframework.context.annotation.ScopeMetadataResolver}
 *                  * {@link BeanDefinition} 解析 - 内部 API 实现
 *                  * {@link BeanDefinition} 处理 - {@link org.springframework.context.annotation.AnnotationConfigUtils#processCommonDefinitionAnnotations(AnnotatedBeanDefinition)}
 *                  * {@link BeanDefinition} 注册 - {@link org.springframework.beans.factory.support.BeanDefinitionRegistry}
 *
 *
 *        116 | 基于XML资源装载Spring IoC容器配置元信息
 *
 *           spring.handlers
 *           spring.schemas
 *
 * @author zhengshijun
 * @version created on 2020/11/13.
 */
@SuppressWarnings("JavadocReference")
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
