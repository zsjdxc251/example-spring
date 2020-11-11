







## 面试题

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

