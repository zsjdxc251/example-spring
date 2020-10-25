package org.example.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.AutowireCandidateResolver;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 *
 *  65 | 依赖处理过程：依赖处理时会发生什么？其中与依赖查找的差异在哪？
 *
 *    依赖处理过程
 *
 * 入口  {@link DefaultListableBeanFactory#resolveDependency(org.springframework.beans.factory.config.DependencyDescriptor, java.lang.String, java.util.Set, org.springframework.beans.TypeConverter)}
 *
 * 依赖描述符  {@link DependencyDescriptor}
 *
 * 自定绑定候选对象处理器  {@link AutowireCandidateResolver}
 *
 *
 *
 * 66 | @Autowired注入：@Autowired注入的规则和原理有哪些？
 *
 *     {@link AutowiredAnnotationBeanPostProcessor}
 *
 *
 *
 *
 *  67 | JSR-330 @Inject注入：@Inject与@Autowired的注入原理有怎样的联系？
 *
 *
 *
 *  68 | Java通用注解注入原理：Spring是如何实现@Resource和@EJB等注解注入的？
 *     {@link CommonAnnotationBeanPostProcessor}
 *
 *
 *  69 | 自定义依赖注入注解：如何最简化实现自定义依赖注入注解？
 *     {@link DependencyInjectionResolutionSample#customBeanPostProcessor()}
 *
 *
 *
 *
 * 反射工具
 *  {@link Modifier#isStatic(int)}
 *
 * @author zhengshijun
 * @version created on 2020/10/24.
 */
@Configuration
public class DependencyInjectionResolutionSample {

	@Autowired
	private  Optional<Member> optionalMember;


	@CustomInjected
	public Member member;

	@Bean
	public Member member() {

		return new Member("zsj", 25);
	}


//	@Bean(AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//	public static   AutowiredAnnotationBeanPostProcessor customBeanPostProcessor(){
//		AutowiredAnnotationBeanPostProcessor postProcessor = new AutowiredAnnotationBeanPostProcessor();
//		        Set<Class<? extends Annotation>> autowiredAnnotationTypes =
//                new LinkedHashSet<>(Arrays.asList(Autowired.class, CustomInjected.class));
//		        postProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//		return postProcessor;
//	}


	@Order(Ordered.LOWEST_PRECEDENCE - 3)
	@Bean
	public static  AutowiredAnnotationBeanPostProcessor customBeanPostProcessor(){
		AutowiredAnnotationBeanPostProcessor postProcessor = new AutowiredAnnotationBeanPostProcessor();
		postProcessor.setAutowiredAnnotationType(CustomInjected.class);
		return postProcessor;
	}


	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DependencyInjectionResolutionSample.class);


		DependencyInjectionResolutionSample dependencyInjectionResolution = applicationContext.getBean(DependencyInjectionResolutionSample.class);

		System.out.println(dependencyInjectionResolution.optionalMember);
		System.out.println(dependencyInjectionResolution.member);
	}
}
