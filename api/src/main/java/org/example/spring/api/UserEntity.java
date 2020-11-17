package org.example.spring.api;

/**
 * @author zhengshijun
 * @version created on 2020/11/16.
 */
public class UserEntity {

	private Long id;

	private String name;

	private City city;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public static enum City {
		/**
		 * 背景
		 */
		SHENZHEN,

		/**
		 * 北京
		 */
		BEIJING,

		/**
		 * 杭州
		 */
		HANGZHOU,
		/**
		 * 上海
		 */
		SHANGHAI;
	}
}
