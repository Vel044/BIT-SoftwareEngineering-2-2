package cn.com.example.ex4;

public class TestScope2 {
	
	public static void main(String[] args) {
		// 定义一个方法局部变量做循环变量
		int i;
		for(i=0; i<10; i++){
			System.out.println(i);
		}
	}

}
