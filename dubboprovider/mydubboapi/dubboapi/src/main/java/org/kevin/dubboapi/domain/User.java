package org.kevin.dubboapi.domain;

import java.io.Serializable;

public class User extends BaseUser implements Serializable {

	private static final long serialVersionUID = -6230036781204173185L;
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
