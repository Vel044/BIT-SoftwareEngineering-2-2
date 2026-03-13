package cn.com.example.ex3;

public class Person {
	public static String nationality="Chinese";
	private String name;

	public static String getNationality() {   //静态方法访问静态成员

		//return name+":"+nationality;

		return nationality;
	}

	public static void setNationality(String nationality) {  //静态方法访问静态成员
		Person.nationality = nationality;
	}

	public void sayHello(){ //非静态方法可以引用静态成员
		final int a=0;
		System.out.println("hello,"+nationality+"!");
	}

	public static void main(String[] args) {
		new Person().sayHello();  //创建Person类的匿名对象调用sayHello方法
	}
}