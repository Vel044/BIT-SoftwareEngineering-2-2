package inner;//: c08:Parcel10.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Static inner classes.

public class Parcel10 {

  int x=10;
  private  static class PContents
  implements Contents {
    static private int i = 11;

    public int value() {
     //i=i+x; 不可以使用非静态成员
      return i; }
  }
  protected static class PDestination
      implements Destination {
    private String label;
     PDestination(String whereTo) {
      label = whereTo;
    }
    public String readLabel() { return label; }
    // Static inner classes can contain 
    // other static elements:
    public static void f() {}
    static int x = 10;
    static class AnotherLevel {
      public static void f() {}
      static int x = 10;
    }
  }
  public static Destination dest(String s) {
    return new PDestination(s);
  }
  public static Contents cont() {
    return new PContents();
  }
  public static void main(String[] args) {
    Contents c = cont();
    Destination d = dest("Tanzania");
  }
} ///:~