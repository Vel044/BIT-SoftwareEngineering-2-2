package cn.example.ex3.sub;

public class AccessDemo{  //子类AccessDemo与父类A不在同一包下

	//在子类的方法中可以直接使用 能被访问的父类成员
	public void getInfo(){
		A a=new A();
		System.out.println(a.a);  //可以访问类A的public成员
		System.out.println(a.b);	//可以访问类A的protected成员

		System.out.println(a.c);	//可以访问类A的package成员
		//System.out.println(a.d);	//不可以访问类A的private成员
	}
}