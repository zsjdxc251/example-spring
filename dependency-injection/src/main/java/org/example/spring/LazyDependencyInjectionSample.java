package org.example.spring;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 *
 *    迟依赖注入：如何实现延迟执行依赖注入？与延迟依赖查找是类似的吗？
 *
 * @author zhengshijun
 * @version created on 2020/10/23.
 */
@Configuration
public class LazyDependencyInjectionSample {


	@Autowired
	private ObjectProvider<Member> objectProvider;


	@Autowired
	private ObjectFactory<Member> objectFactory;

	@Bean
	@Lazy
	public Member member() {
		return new Member("zsj", 25);
	}

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(LazyDependencyInjectionSample.class);

		LazyDependencyInjectionSample lazyDependencyInjection = applicationContext.getBean(LazyDependencyInjectionSample.class);


		System.out.println(applicationContext.getBean(Member.class));

		System.out.println(lazyDependencyInjection.objectProvider.getObject());

		lazyDependencyInjection.objectProvider.forEach(System.out::println);

		lazyDependencyInjection.objectFactory.getObject();

	}
}
