package org.example.spring;

import org.example.spring.ext.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;

import javax.annotation.PostConstruct;

/**
 *
 *    121 | 基于YAML资源装载外部化配置
 *
 *    
 * @author zhengshijun
 * @version created on 2020/11/19.
 */
@Configuration
@PropertySource(name = "custom-source", value = "META-INF/property_source_bean.xml", factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceSample {

	@Value("${user.name:?}")
	private String name;

	@PostConstruct
	public void init() {
		System.out.println("name:" + name);
	}

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext();
		applicationContext.register(AnnotatedYamlPropertySourceSample.class);
		applicationContext.refresh();

		System.out.println(applicationContext.getEnvironment().getPropertySources());

	}
}
