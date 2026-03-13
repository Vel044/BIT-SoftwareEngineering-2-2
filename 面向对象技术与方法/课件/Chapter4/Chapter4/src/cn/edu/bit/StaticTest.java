package cn.edu.bit;
class StaticDefiniton{
	static void sayHello(){
		System.out.println("Hello world!");
	}
	static int i=1;
}
public class StaticTest {

	
	public static void main(String[] args) {
        int b=StaticDefiniton.i;
		StaticDefiniton.sayHello();

		StaticDefiniton definitonTest=new StaticDefiniton();
		definitonTest.sayHello();//不推荐
	}
}
