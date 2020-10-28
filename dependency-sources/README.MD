







## 面试题

### 注入和查找的依赖来源是否相同？

> 否，依赖查找的来源仅限于Spring BeanDefinition 已经 单例对象，而依赖注入来源包括Resolvable Dependency 以及 @Value 所标注的外部化配置



### 单例对象能在 IOC 容器启动后注册吗？

> 可以的，单例对象与BeanDefinition 不同，BeanDefinition 会被ConfigurableListableBeanFactory#freezConfiguration() 方法影响，从而冻结注册，单例对象则没有这个限制



### Spring 依赖注入来源有哪些？

> Spring Beandefinition 
>
> 单例对象
>
> Resolvable Dependency
>
> @Value 外部和配置

