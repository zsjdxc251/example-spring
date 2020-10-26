package org.example.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ResourceLoader;

/**
 *
 * 72 | 依赖注入的来源：难道依赖注入的来源与依赖查找的不同吗？
 *
 *    {@code AbstractApplicationContext#prepareBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)}
 *
 *
 *
 * 74 | Spring Bean Definition作为依赖来源：Spring Bean的来源
 *
 *   {@link BeanDefinitionRegistry}
 *       {@link DefaultListableBeanFactory#registerBeanDefinition(java.lang.String, org.springframework.beans.factory.config.BeanDefinition)}
 *
 *
 *
 *  75 | 单例对象作为依赖来源：单体对象与普通Spring Bean存在哪些差异？
 *
 *   {@link SingletonBeanRegistry}
 *       {@link DefaultListableBeanFactory#registerSingleton(java.lang.String, java.lang.Object)}
 *
 *
 * @author zhengshijun
 * @version created on 2020/10/26.
 */
public class DependencySourcesSample implements InitializingBean {

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private ApplicationContext applicationContext;


	/**
	 *  依赖注入 和 依赖来源
	 *
	 *  依赖注入会多一个非Spring 容器管理对象
	 *
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("beanFactory == applicationContext : "+(beanFactory == applicationContext));

		System.out.println("beanFactory == applicationContext.getBeanFactory() : " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));

		System.out.println("resourceLoader == applicationContext : "+ (resourceLoader == applicationContext));

		System.out.println("applicationEventPublisher == applicationContext : " + (applicationEventPublisher == applicationContext));


		getBean(BeanFactory.class);

		getBean(ResourceLoader.class);

		getBean(ApplicationEventPublisher.class);

		getBean(ApplicationContext.class);
	}

	private <T> T getBean(Class<T> clazz){

		try {
			return beanFactory.getBean(clazz);
		} catch (NoSuchBeanDefinitionException e) {
			System.err.println("找不到 "+clazz+" 异常");
		}
		return null;
	}


	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DependencySourcesSample.class);

	}


}
