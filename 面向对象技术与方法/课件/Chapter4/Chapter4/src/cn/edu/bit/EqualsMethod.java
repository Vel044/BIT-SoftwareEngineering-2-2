package cn.edu.bit;//: c03:EqualsMethod.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.

public class EqualsMethod {
  public static void main(String[] args) {
    String n1 = new String("a");
    String n2 = new String("a");;
    System.out.println(n1.equals(n2));
  }
} ///:~