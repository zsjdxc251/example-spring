package org.example.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 *
 *   76 | 非Spring容器管理对象作为依赖来源：如何理解ResolvableDependency？
 *
 *      {@link ConfigurableListableBeanFactory#registerResolvableDependency(java.lang.Class, java.lang.Object)}
 *
 *    限制
 *       无生命周期
 *       无法实现延迟初始化 Bean
 *       无法通过依赖查找
 *       只能用于依赖注入 不能用于依赖查找
 *
 * @author zhengshijun
 * @version created on 2020/10/27.
 */
public class ResolvableDependencySourcesSample {


	@Autowired
	private String value;


	@PostConstruct
	public void init(){

		System.out.println("value  : " +value);
	}

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(ResolvableDependencySourcesSample.class);


		applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
			beanFactory.registerResolvableDependency(String.class,"ResolvableDependency Bean");
		});
		applicationContext.refresh();




		applicationContext.close();

	}
}
