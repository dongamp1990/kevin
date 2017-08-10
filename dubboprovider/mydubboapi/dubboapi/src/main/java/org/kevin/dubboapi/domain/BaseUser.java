package org.kevin.dubboapi.domain;

import java.io.Serializable;

public class BaseUser implements Serializable {
	private static final long serialVersionUID = -8896834890231658563L;
	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
