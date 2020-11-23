package org.example.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.NonNull;

import javax.annotation.PostConstruct;

/**
 *
 *    132 | 依赖注入ResourceLoader：除了ResourceLoaderAware回调注入，还有哪些注入方法？
 *
 * @see org.springframework.core.io.ResourceLoader
 * @author zhengshijun
 * @version created on 2020/11/23.
 */
public class InjectingResourceLoaderSample implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;

	@Autowired
	private ResourceLoader resourceLoaderAutowired;


	@Autowired
	private AbstractApplicationContext applicationContext;


	@Override
	public void setResourceLoader(@NonNull ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@PostConstruct
	public void init(){

		System.out.println("resourceLoader==resourceLoaderAutowired : " + (resourceLoader == resourceLoaderAutowired));

		System.out.println("resourceLoader==applicationContext : " + (resourceLoader == applicationContext));
	}




	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(InjectingResourceLoaderSample.class);

		applicationContext.refresh();

		applicationContext.start();

	}


}
