//: innerclasses/DotThis.java
// Qualifying access to the outer-class object.

public class DotThis {
  int len=20;
  void f() { System.out.println("DotThis.f()"); }
  public class Inner {
    int len=200;
    public DotThis outer() {
      System.out.println(this.len);
      System.out.println(DotThis.this.len);
      return DotThis.this;
      // A plain "this" would be Inner's "this"
    }
  }
  public Inner inner() { return new Inner(); }
  public static void main(String[] args) {
    DotThis dt = new DotThis();
    Inner dti =dt.inner();
    dti.outer().f();
  }
} /* Output:
DotThis.f()
*///:~
