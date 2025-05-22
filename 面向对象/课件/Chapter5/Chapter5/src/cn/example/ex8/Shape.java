package cn.example.ex8;

public class Shape {
	private String name;
	
	public Shape() {
		super();		
		System.out.println("Shape()...");
	}

	public Shape(String name) {
		super();
		this.name = name;
		System.out.println("Shape(String)...");
	}

	public double getArea() {
		return 0;
	}
}
