package ex6;

import java.io.Serializable;

public class Teacher implements Serializable{

	private static final long serialVersionUID = -6989255306342194450L;
	private String name;

	public Teacher(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}