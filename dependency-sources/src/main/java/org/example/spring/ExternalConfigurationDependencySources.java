package org.example.spring;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.AutowireCandidateResolver;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringValueResolver;

import javax.annotation.PostConstruct;

/**
 *    77 | 外部化配置作为依赖来源：@Value是如何将外部化配置注入Spring Bean的？
 *
 *      {@link Value}
 *         {@link AutowiredAnnotationBeanPostProcessor#AutowiredAnnotationBeanPostProcessor()}
 *
 *           {@code org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.AutowiredFieldElement#inject(java.lang.Object, java.lang.String, org.springframework.beans.PropertyValues)}
 *
 *              {@link DefaultListableBeanFactory#resolveDependency(org.springframework.beans.factory.config.DependencyDescriptor, java.lang.String, java.util.Set, org.springframework.beans.TypeConverter)}
 *                  {@link DefaultListableBeanFactory#doResolveDependency(org.springframework.beans.factory.config.DependencyDescriptor, java.lang.String, java.util.Set, org.springframework.beans.TypeConverter)}
 *                      {@link AutowireCandidateResolver#getSuggestedValue(org.springframework.beans.factory.config.DependencyDescriptor)}
 *                         {@link AbstractBeanFactory#resolveEmbeddedValue(java.lang.String)}
 *
 *
 *         {@link EmbeddedValueResolverAware}
 *            {@link StringValueResolver}
 * @author zhengshijun
 * @version created on 2020/10/27.
 */
@Configuration
@PropertySource(value = "META-INF/default.properties",encoding = "UTF-8")
public class ExternalConfigurationDependencySources {

	@Value("${member.id:-1}")
	private Long id;

	@Value("${member.name:name}")
	private String name;

	@Value("${member.resource:classpath://default.properties}")
	private Resource resource;

	@PostConstruct
	public void init(){

		System.out.println("id : "+id);
		System.out.println("name : "+name);
		System.out.println("resource : "+resource);

	}


	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();


		applicationContext.register(ExternalConfigurationDependencySources.class);


		applicationContext.refresh();
		applicationContext.start();


		applicationContext.close();

	}
}
