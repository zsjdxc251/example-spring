







## 面试题

### Spring 配置资源中哪些常见类型？

> Xml 资源
>
> Properties 资源
>
> YAML 资源



### 请列举不同类型Spring 配置资源

* XML 资源
  * 普通Bean Definition XML 配置资源 - *.xml
* Properties 资源
  * 普通 Properties 格式资源 -*.properties
  * Spring Handler 实现映射文件 - META-INF/spring.handlers
  * Spring Schema 资源映射文件 - META-INF/spring.schemas
* YAML 资源
  * 普通 YAML 配置资源 - *.yaml 或 *.yml



### Java 标准资源管理扩展的步骤

* 简易
  * 实现 URLStreamHandler 并且放置在sun.net.www.protocol.${protocol}.Handler 包下
* 自定义实现
  * 实现 URLStreamHandler
  * 添加-Djava.protocol.handler.pkgs `java.net.URL#protocolPathProp` 启动参数，指向 URLStreamHandler 实现类的包下
* 高级实现
  * 实现 URLStreamHandlerFactory 并传递到URL之中

`java.net.URL#getURLStreamHandler`