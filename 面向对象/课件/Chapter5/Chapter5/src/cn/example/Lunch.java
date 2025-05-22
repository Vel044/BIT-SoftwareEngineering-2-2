package cn.example;//: c05:Lunch.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Demonstrates class access specifiers.
// Make a class effectively private
// with private constructors:

class Soup {
    private static Soup ps1 ;
    private Soup() {}



    // (2) Create a static object and
  // return a reference upon request.
  // (The "Singleton" pattern):

  public static Soup access() {
        if(ps1==null){
            ps1=new Soup();
        }

        return ps1;
  }
  public void f() {}
}

class Sandwich { // Uses Lunch
  void f() { new Lunch(); }
}

// Only one public class allowed per file:
public class Lunch {
  void test() {
    // Can't do this! Private constructor:
   // Soup priv1 = new Soup();

    Sandwich f1 = new Sandwich();
    Soup.access().f();

  }
} ///:~