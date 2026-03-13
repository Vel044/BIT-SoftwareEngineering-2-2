package cn.example.ex8;

public class Circle extends Ellipse{

	public Circle() {
		super();
		System.out.println("Cicle()...");
	}

	public Circle(String name, double r) {
		super(name, r, r);
		System.out.println("Circle(String,double)...");
	}
	
	public double getArea(){
		return Math.PI*getA()*getB();
	}
}
