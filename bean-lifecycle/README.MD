







## 面试题



### BeanPostProcessor 的使用场景有哪些？

> BeanPostProcessor 提供 Spring Bean 初始化前和初始化后的生命周期回调，分别对应postProcessBeforeInitialization 以及 postProcesssAfterInitialization 方法，允许对关心 Bean 进行扩展或者是替换
>
> 其中，ApplicationContext 相关Aware 回调也是基于BeanPostProcessor实现，即ApplicationContextAwareProcessor



### BeanFactoryProcessor 与BeanPostProcessor 的区别

> BeanFactoryProcessor 是 Spring BeanFactory （实际为 ConfigurableListableBeanFactory）的后置处理器，用于扩展BeanFactory,或通过BeanFactory 进行依赖查找和依赖注入。
>
> 且 BeanFactoryPostProcessor 必须有 Spring ApplicationContext 执行，BeanFactory 无法与其进行交换
>
> 而BeanPostProcessor 则直接与BeanFactory 关联，属于 N对 1 的关系。

### BeanFactory 是怎么处理Bean 生命周期？

> BeanFactory 的默认实现为DefaultListableBeanFactory ,其中 Bean 生命周期与方法映射如下：
>
> BeanDefinition 注册阶段 - registerBeanDefinition
>
> BeanDefinition 合并阶段 - getMergedBeanDefinition
>
> Bean 实例化前阶段 - resolveBeforeInstantiation
>
> Bean 实例化阶段 - createBeanInstance
>
> Bean 初始化后阶段 - populateBean 
>
> Bean 属性赋值前阶段 - populateBean
>
> Bean 属性赋值阶段 - populateBean
>
> Bean Aware 接口回调阶段 - initializeBean
>
> Bean 初始化前阶段 - initializeBean
>
> Bean 初始化阶段 - initializeBean
>
> Bean 初始化完成阶段 - preInstantiateSingletons
>
> Bean 销毁前阶段 - destroyBean
>
> Bean 销毁阶段 - destroyBean

