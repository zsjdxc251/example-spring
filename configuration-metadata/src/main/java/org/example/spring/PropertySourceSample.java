package org.example.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *   120 |  基于Properties资源装载外部化配置
 *
 *     {@link PropertySource}
 *
 * @author zhengshijun
 * @version created on 2020/11/19.
 */
public class PropertySourceSample {

	@Value("${user.name:?}")
	private String name;

	@PostConstruct
	public void init(){

		System.out.println("name:"+name);
	}


	public static void main(String[] args) {

		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(PropertySourceSample.class);

		Map<String, Object> propertiesSource = new HashMap<>();
		propertiesSource.put("user.name", "zsj");
		PropertySource<?> propertySource = new MapPropertySource("custom-property-source",propertiesSource);
		applicationContext.getEnvironment().getPropertySources().addFirst(propertySource);
		applicationContext.start();

		System.out.println(applicationContext.getEnvironment().getPropertySources());

	}
}
