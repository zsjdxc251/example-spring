package org.example.spring;

/**
 * @author zhengshijun
 * @version created on 2020/10/23.
 */
public class Member {

	private String name;

	private Integer age;


	public Member(String name,Integer age){
		this.name = name;
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Member{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
