package cn.example;//: c05:IceCream.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Demonstrates "private" keyword.

class Sundae {
  private Sundae() {
  	System.out.println("Sundae");
  }
  static Sundae makeASundae() { 
    return new Sundae(); 
  }
}

public class IceCream {
  public static void main(String[] args) {
    // Sundae x = new Sundae();
    Sundae x = Sundae.makeASundae();
  }
} ///:~