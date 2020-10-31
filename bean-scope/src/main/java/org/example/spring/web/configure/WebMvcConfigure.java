package org.example.spring.web.configure;

import org.example.spring.SampleEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhengshijun
 * @version created on 2020/10/31.
 */
@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {


	@Bean
	@RequestScope
	public SampleEntity sampleEntity(){
		return new SampleEntity(System.nanoTime());
	}

}
