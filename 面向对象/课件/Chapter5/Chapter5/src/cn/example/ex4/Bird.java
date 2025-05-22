package cn.example.ex4;

public class Bird extends Animal{

	public void move(){
		super.move();	//调用父类的move()方法
		System.out.println("我可以在天空飞翔.....");
	}

	public static void main(String[] args){
		Bird bird = new Bird();
		bird.move();   //输出"我可以在天空飞翔....."
		System.out.println(bird);
	}

}