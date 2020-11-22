package org.example.spring;

import org.example.spring.utils.ResourceHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 *
 *    131 | 依赖注入Spring Resource：如何在XML和Java注解场景注入Resource对象？
 *
 *
 * @see org.springframework.core.io.Resource
 * @see org.springframework.beans.factory.annotation.Value
 * @author zhengshijun
 * @version created on 2020/11/22.
 */
public class InjectingResourceSample {

	@Value("classpath:/META-INF/default.properties")
	private Resource defaultResourceProperties;


	@Value("classpath*:/META-INF/*.properties")
	private Resource[] resourcesProperties;

	@Value("${user.dir}")
	private String currentProjectRootPath;

	@PostConstruct
	public void init(){


		System.out.println(ResourceHelper.getContent(defaultResourceProperties));

		Stream.of(resourcesProperties).map(ResourceHelper::getContent).forEach(System.out::println);

		System.out.println(currentProjectRootPath);
	}


	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(InjectingResourceSample.class);
		applicationContext.refresh();
		applicationContext.start();


	}
}
