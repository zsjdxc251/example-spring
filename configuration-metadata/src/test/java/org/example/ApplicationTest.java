package org.example;

import org.example.spring.api.ContextEntity;
import org.example.spring.api.SampleEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * @author zhengshijun
 * @version created on 2020/11/14.
 */
public class ApplicationTest {

	public static void main(String[] args) {

		AbstractApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(SampleEntity.class);
		AbstractApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(ContextEntity.class);
		applicationContext1.setParent(applicationContext2);

		applicationContext1.start();
		applicationContext2.start();

		System.out.println(String.join(",\n",applicationContext1.getBeanDefinitionNames()));
		System.out.println("\t");
		System.out.println(String.join(",\n",applicationContext2.getBeanDefinitionNames()));

		System.out.println("applicationContext1 SampleEntity : " + getBean(applicationContext1,SampleEntity.class));
		System.out.println("applicationContext1 ContextEntity :" + getBean(applicationContext1,ContextEntity.class));



		System.out.println("applicationContext2 SampleEntity : " + getBean(applicationContext2,SampleEntity.class));
		System.out.println("applicationContext2 ContextEntity : " + getBean(applicationContext2,ContextEntity.class));
	}

	public static <T> T getBean(ApplicationContext context,Class<T> clazz){
		try {
			return context.getBean(clazz);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
