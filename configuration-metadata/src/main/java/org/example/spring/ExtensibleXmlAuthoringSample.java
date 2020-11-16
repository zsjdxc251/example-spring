package org.example.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.beans.factory.xml.NamespaceHandlerResolver;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;

/**
 *
 *    118 | 基于Extensible XML authoring 扩展Spring XML元素
 *
 *       Xml 扩展
 *         * 编写 Xml Schema 文件 ： 定义Xml结构
 *         * 自定义 {@link NamespaceHandler} 实现 ： 命名空间绑定
 *         * 自定义 {@link BeanDefinitionParser} 实现 Xml 元素与 {@link BeanDefinition} 解析
 *
 *
 *
 *    119 | Extensible XML authoring扩展原理
 *       {@link AbstractApplicationContext#obtainFreshBeanFactory()}
 *       -- {@link AbstractRefreshableApplicationContext#refreshBeanFactory()}
 *       -- -- {@link AbstractXmlApplicationContext#loadBeanDefinitions(org.springframework.beans.factory.support.DefaultListableBeanFactory)}
 *       -- -- -- {@link XmlBeanDefinitionReader#doLoadBeanDefinitions(org.xml.sax.InputSource, org.springframework.core.io.Resource)}
 *       -- -- -- -- {@link XmlBeanDefinitionReader#registerBeanDefinitions(org.w3c.dom.Document, org.springframework.core.io.Resource)}
 *       -- -- -- -- -- {@link DefaultBeanDefinitionDocumentReader#doRegisterBeanDefinitions(org.w3c.dom.Element)}
 *       -- -- -- -- -- -- {@link DefaultBeanDefinitionDocumentReader#parseBeanDefinitions(org.w3c.dom.Element, org.springframework.beans.factory.xml.BeanDefinitionParserDelegate)}
 *       -- -- -- -- -- -- -- {@link BeanDefinitionParserDelegate#parseCustomElement(org.w3c.dom.Element, org.springframework.beans.factory.config.BeanDefinition)}
 *       -- -- -- -- -- -- -- -- {@link BeanDefinitionParserDelegate#getNamespaceURI(org.w3c.dom.Node)} // 命名空间url
 *       -- -- -- -- -- -- -- -- {@link NamespaceHandlerResolver#resolve(java.lang.String)}
 *       -- -- -- -- -- -- -- -- -- {@link NamespaceHandler#init()}
 *       -- -- -- -- -- -- -- -- {@link NamespaceHandlerSupport#parse(org.w3c.dom.Element, org.springframework.beans.factory.xml.ParserContext)}
 *       -- -- -- -- -- -- -- -- -- {@link AbstractBeanDefinitionParser#parse(org.w3c.dom.Element, org.springframework.beans.factory.xml.ParserContext)}
 *       -- -- -- -- -- -- -- -- -- -- {@link AbstractSingleBeanDefinitionParser#parseInternal(org.w3c.dom.Element, org.springframework.beans.factory.xml.ParserContext)}
 *       -- -- -- -- -- -- -- -- -- -- -- {@link AbstractSingleBeanDefinitionParser#doParse(org.w3c.dom.Element, org.springframework.beans.factory.xml.ParserContext, org.springframework.beans.factory.support.BeanDefinitionBuilder)}
 *
 * @author zhengshijun
 * @version created on 2020/11/15.
 */
@SuppressWarnings("JavadocReference")
public class ExtensibleXmlAuthoringSample {

	public static void main(String[] args) {

	}
}
