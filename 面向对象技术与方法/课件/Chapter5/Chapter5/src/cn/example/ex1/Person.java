package cn.example.ex1;

import java.util.Date;

public class Person {
	private String id;
	private String name;
	private Date birthday;
	
	public String getId() {
		return id;
	}
	public Person() {	
	}
	public Person(String id, String name, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
