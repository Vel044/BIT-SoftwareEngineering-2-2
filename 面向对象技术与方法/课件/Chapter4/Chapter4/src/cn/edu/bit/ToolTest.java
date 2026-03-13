package cn.edu.bit;//: c05:ToolTest.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Uses the tools library.
import com.bruceeckel.simple.Vector;
import com.bruceeckel.tools.*;
import cn.com.example.ex1.Hotel;

public class ToolTest {
  public static void main(String[] args) {
    P.rintln("Available from now on!");
    P.rintln("" + 100); // Force it to be a String
    P.rintln("" + 100L);
    P.rintln("" + 3.14159);
    //P.rintln(100);
    Vector a=new Vector();//默认包
   Hotel b=new Hotel("国际交流中心");//不在一个包中


  }
} ///:~