package org.example.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.util.StringUtils;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 *   bean 销毁生命周期
 *
 *   102 | Spring Bean销毁前阶段：DestructionAwareBeanPostProcessor用在怎样的场景?
 *    {@link DestructionAwareBeanPostProcessor}
 *    // 销毁
 *    -- {@link AbstractBeanFactory#destroyBean(java.lang.String, java.lang.Object)}
 *    // 销毁过程及顺序
 *    -- -- {@link org.springframework.beans.factory.support.DisposableBeanAdapter#destroy()}
 *
 *
 *    103 | Spring Bean销毁阶段：@PreDestroy、DisposableBean以及自定义方法
 *
 *
 *    104 | Spring Bean垃圾收集（GC）：何时需要GC Spring Bean？
 *
 * @author zhengshijun
 * @version created on 2020/11/11.
 */
@SuppressWarnings("JavadocReference")
public class BeanDestroyLifecycleSample {


	public static void main(String[] args) throws InterruptedException {

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		beanFactory.addBeanPostProcessor(new CustomDestructionAwareBeanPostProcessor());
		beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
		BeanDefinition beanDefinition =
				BeanDefinitionBuilder.genericBeanDefinition(DestroySampleEntity.class).setDestroyMethodName("doDestroy").getBeanDefinition();
		beanFactory.registerBeanDefinition("DestroySampleEntity", beanDefinition);

		DestroySampleEntity sampleEntity = beanFactory.getBean(DestroySampleEntity.class);
		System.out.println(sampleEntity);
		beanFactory.destroyBean("DestroySampleEntity", sampleEntity);
		System.out.println(sampleEntity);

		beanFactory.destroySingletons();

		System.gc();

		TimeUnit.SECONDS.sleep(1);

		System.gc();
	}


	private static class DestroySampleEntity implements DisposableBean {

		private String name;

		public DestroySampleEntity() {
			this.name = "DestroySampleEntity";
		}


		@PreDestroy
		public void preDestroy() {
			System.out.println("preDestroy");
		}

		@Override
		public void destroy() {
			System.out.println("destroy");
		}

		public void doDestroy() {
			System.out.println("doDestroy");
		}


		@Override
		public String toString() {
			return "DestroySampleEntity{" +
					"name='" + name + '\'' +
					'}';
		}

		@Override
		protected void finalize() throws Throwable {
			System.out.println("执行了finalize");
		}
	}

	private static class CustomDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

		@Override
		public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
			if (StringUtils.endsWithIgnoreCase(beanName, "DestroySampleEntity")) {
				DestroySampleEntity.class.cast(bean).name = "销毁了bean";
				System.out.println("postProcessBeforeDestruction bean :" + bean);
			}
		}
	}
}
