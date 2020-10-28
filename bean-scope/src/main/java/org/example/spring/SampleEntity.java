package org.example.spring;

/**
 * @author zhengshijun
 * @version created on 2020/10/28.
 */
public class SampleEntity {

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
}
