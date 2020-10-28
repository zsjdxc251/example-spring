package org.example.spring;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *
 *     81 | "prototype" Bean作用域：原型Bean在哪些场景下会创建新的实例？
 *
 *       总结
 *           结论一：
 *          Singleton Bean 无论依赖查找还是依赖注入，均为同一个对象
 *          Prototype Bean 无论依赖查找还是依赖注入，均为新生成的对象
 *
 *          结论二：
 *          如果依赖注入集合类型的对象，Singleton Bean 和 Prototype Bean 均会存在一个
 *          Prototype Bean 有别于其他地方的依赖注入 Prototype Bean
 *
 *          结论三：
 *          无论是 Singleton 还是 Prototype Bean 均会执行初始化方法回调
 *          不过仅 Singleton Bean 会执行销毁方法回调
 *
 * @author zhengshijun
 * @version created on 2020/10/28.
 */
public class BeanScopeSample {

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public SampleEntity sampleEntityPrototype(){

		return new SampleEntity(System.nanoTime());
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public SampleEntity sampleEntitySingleton(){

		return new SampleEntity(System.nanoTime());
	}

	@PostConstruct
	public void init(){


	}

	@PreDestroy
	public void destroy(){

	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		applicationContext.register(BeanScopeSample.class);

		applicationContext.refresh();
	}
}
