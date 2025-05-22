package cn.edu.bit;

public class StaticErr {
    int age=20;
    void info(){
        System.out.println("hello,world");
    }
    StaticErr(){
        age=30;
    }
   static void sayHello(){
       // info();
    }

    //static int b=a;
    public static void main(String[] args){
        //System.out.println(age);
        //info();
        StaticErr a=new StaticErr();
        a.info();

    }
}
