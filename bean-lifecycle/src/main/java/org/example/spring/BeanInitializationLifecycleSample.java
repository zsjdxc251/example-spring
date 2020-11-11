package org.example.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;

/**
 *
 *
 *  {@link AbstractAutowireCapableBeanFactory#initializeBean(java.lang.String, java.lang.Object, org.springframework.beans.factory.support.RootBeanDefinition)}
 *
 *    98 | Spring Bean初始化前阶段：BeanPostProcessor
 *      {@link BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)}
 *       {@link AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization(java.lang.Object, java.lang.String)}
 *
 *
 *
 *    99 | Spring Bean初始化阶段：@PostConstruct、InitializingBean以及自定义方法
 *
 *      {@link AbstractAutowireCapableBeanFactory#invokeInitMethods(java.lang.String, java.lang.Object, org.springframework.beans.factory.support.RootBeanDefinition)}
 *
 *
 *    100 | Spring Bean初始化后阶段：BeanPostProcessor
 *
 *      {@link BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)}
 *         {@link AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization(java.lang.Object, java.lang.String)} 应用
 *
 *
 *    101 | Spring Bean初始化完成阶段：SmartInitializingSingleton (spring 4.1以上)
 *      {@link SmartInitializingSingleton}
 *      {@link AbstractApplicationContext#refresh()}
 *      -- {@link AbstractApplicationContext#finishBeanFactoryInitialization(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)}
 *      -- -- {@link ConfigurableListableBeanFactory#preInstantiateSingletons()}
 *
 *
 *
 * @author zhengshijun
 * @version created on 2020/11/9.
 */
@SuppressWarnings("JavadocReference")
public class BeanInitializationLifecycleSample {

	public static void main(String[] args) {

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		beanFactory.addBeanPostProcessor(new CustomBeanPostProcessor());
		// 添加处理 {@link javax.annotation.PostConstruct}
		beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("META-INF/beans.xml"));

		BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(InitSampleEntity.class).setInitMethodName("init").getBeanDefinition();
		beanFactory.registerBeanDefinition("InitSampleEntity",beanDefinition);
        // SmartInitializingSingleton
		beanFactory.preInstantiateSingletons();

		System.out.println(beanFactory.getBean(InitSampleEntity.class));



	}


	private static class  InitSampleEntity implements InitializingBean,SmartInitializingSingleton {
		private String name;

		@Override
		public void afterPropertiesSet() throws Exception {
			System.out.println("afterPropertiesSet");
		}

		@PostConstruct
		public void postConstruct(){
			System.out.println("postConstruct");
		}

		public void init(){
			System.out.println("init");
		}

		@Override
		public String toString() {
			return "InitSampleEntity{" +
					"name='" + name + '\'' +
					'}';
		}

		@Override
		public void afterSingletonsInstantiated() {
			System.out.println("afterSingletonsInstantiated");
		}
	}

	static class CustomBeanPostProcessor implements BeanPostProcessor {
		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
			if (bean instanceof InitSampleEntity){

				((InitSampleEntity) bean).name = "sample";
			}

			// 等于空会终止后面的 BeanPostProcessor
			return bean;
		}

		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
			System.out.println("postProcessAfterInitialization");
			return null;

		}
	}
}
