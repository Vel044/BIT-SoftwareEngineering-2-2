package cn.example;

class A{
	int x=2;	
	public void setx(int i)	 
	{ x = i; } 
	void  printa ()
	{ System.out.println (x);} 	
	}


class B extends A{
	int x=100;
	public void printb() {
		super.x =super.x +10;
		System.out.println("super x is "+super.x +"x="+x);
	}
	
}

public class HideDataMember {
	public static void main(String[] args) {
	A a=new A();
	a.setx(4);
	a.printa();
	
	B b=new B();
	b.printb();
	b.printa();
	
	b.setx(6);
	b.printb();
	b.printa();
	a.printa();
	} 

}
