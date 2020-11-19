package org.example.spring.ext;

import org.example.spring.api.UserEntity;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author zhengshijun
 * @version created on 2020/11/16.
 */
public class CustomBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return UserEntity.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {

		setPropertyValue("id", element, builder);
		setPropertyValue("name", element, builder);
		setPropertyValue("city", element, builder);
	}

	private void setPropertyValue(String attributeName, Element element, BeanDefinitionBuilder builder) {
		String attributeValue = element.getAttribute(attributeName);
		if (StringUtils.hasText(attributeValue)) {
			// -> <property name="" value=""/>
			builder.addPropertyValue(attributeName, attributeValue);
		}
	}
}
