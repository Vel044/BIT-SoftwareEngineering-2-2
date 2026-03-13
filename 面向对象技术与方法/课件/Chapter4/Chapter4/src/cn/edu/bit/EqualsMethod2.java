package cn.edu.bit;//: c03:EqualsMethod2.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.

class Value {
  int i;
  //public boolean equals(Object o){
	//return (o instanceof Value) && i==((Value)o).i;
//}
}

public class EqualsMethod2 {
	public static void main(String[] args) {
		Value v1=new Value();
		Value v2=new Value();
		v1.i=100;
		v2.i=100;
		System.out.println(v1==v2);
		System.out.println(v1.equals(v2));
	}
}