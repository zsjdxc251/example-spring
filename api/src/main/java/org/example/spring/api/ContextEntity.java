package org.example.spring.api;

/**
 * @author zhengshijun
 * @version created on 2020/11/14.
 */
public class ContextEntity {

	private String field;

	private String name;

	private Integer count;


	public String getName() {
		return name;
	}

	public Integer getCount() {
		return count;
	}

	public String getField() {
		return field;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "ContextEntity{" +
				"field='" + field + '\'' +
				", name='" + name + '\'' +
				", count=" + count +
				'}';
	}
}
