package org.example.spring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *   82 | "request" Bean作用域：request Bean会在每次HTTP请求创建新的实例吗？
 *
 *
 *
 *   83 | "session" Bean作用域：session Bean在Spring MVC场景下存在哪些局限性？
 * @author zhengshijun
 * @version created on 2020/10/31.
 */
@SpringBootApplication
public class WebScopeApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebScopeApplication.class,args);
	}
}
