package org.example.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 *
 *    85 | 自定义Bean作用域：设计Bean作用域应该注意哪些原则？
 *
 * @author zhengshijun
 * @version created on 2020/10/31.
 */
public class ThreadLocalScopeSample {

	@Bean
	@Scope(ThreadLocalScope.SCOPE_NAME)
	public SampleEntity sampleEntity() {
		return new SampleEntity(System.nanoTime());
	}


	public static void main(String[] args) throws InterruptedException {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		applicationContext.register(ThreadLocalScopeSample.class);

		applicationContext.addBeanFactoryPostProcessor(beanFactory -> {

			beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
		});
		applicationContext.refresh();


		processBean(applicationContext);


		applicationContext.close();
	}

	private static void processBean(AnnotationConfigApplicationContext applicationContext) throws InterruptedException {
		Thread thread1 = new Thread(() -> {
			getBean(applicationContext);
		},"thread1");

		Thread thread2 = new Thread(() -> {
			getBean(applicationContext);
		},"thread2");


		thread1.start();

		thread2.start();


		thread1.join();

		thread2.join();
	}


	public static void getBean(AnnotationConfigApplicationContext applicationContext) {

		SampleEntity sampleEntity = applicationContext.getBean(SampleEntity.class);

		System.out.println(Thread.currentThread().getName()+":"+sampleEntity);

		sampleEntity = applicationContext.getBean(SampleEntity.class);

		System.out.println(Thread.currentThread().getName()+":"+sampleEntity);
	}
}
