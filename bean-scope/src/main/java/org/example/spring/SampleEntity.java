package org.example.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author zhengshijun
 * @version created on 2020/10/28.
 */
public class SampleEntity implements InitializingBean, DisposableBean {

	private Long id;

	public SampleEntity(){}


	public SampleEntity(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SampleEntity{" +
				"id=" + id +
				'}';
	}


	@Override
	public void destroy() throws Exception {

		System.out.println("id:"+id+"-->>销毁");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("id:"+id+"-->>初始化");
	}
}
