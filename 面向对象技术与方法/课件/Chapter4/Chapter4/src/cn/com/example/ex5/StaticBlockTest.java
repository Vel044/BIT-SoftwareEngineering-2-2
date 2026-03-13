package cn.com.example.ex5;


public class StaticBlockTest {
	static{
		System.out.println("静态代码区，类正在被加载...");
	}

	public StaticBlockTest() {
		System.out.println("创建类的对象...");
	}

	public static void main(String[] args) {
		new StaticBlockTest();
		new StaticBlockTest();
	}
}