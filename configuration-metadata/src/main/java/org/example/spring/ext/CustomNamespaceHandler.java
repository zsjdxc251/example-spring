package org.example.spring.ext;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author zhengshijun
 * @version created on 2020/11/16.
 */
public class CustomNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("user", new CustomBeanDefinitionParser());
	}
}
