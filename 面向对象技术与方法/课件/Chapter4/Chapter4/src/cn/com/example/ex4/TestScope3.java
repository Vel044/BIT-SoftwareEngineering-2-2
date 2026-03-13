package cn.com.example.ex4;

public class TestScope3 {
	
	public static void main(String[] args) {

		// 定义一个代码块局部变量作为循环变量
		for(int i=0; i<10; i++){
			System.out.println(i);
		}
	}

}
