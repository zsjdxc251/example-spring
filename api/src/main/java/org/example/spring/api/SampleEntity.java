package org.example.spring.api;

/**
 * @author zhengshijun
 * @version created on 2020/10/31.
 */
public class SampleEntity {

	private Long id;

	private String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SampleEntity{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
