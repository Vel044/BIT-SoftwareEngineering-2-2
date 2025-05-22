package cn.edu.bit;

import java.util.SplittableRandom;

public class ObjectInit {
    int age;
    String name;//指定初始化
    {
        name="Jack";
        System.out.println(age);
       // System.out.println(name);//对象初始化块
        System.out.println(name);

    }

    ObjectInit(){
        name="Jack";
        System.out.println(age);
        // System.out.println(name);//对象初始化块
        System.out.println(name);
        System.out.println("无参构造器");
    }

    ObjectInit(int age,String name){
        name="Jack";
        System.out.println(age);
        // System.out.println(name);//对象初始化块
        System.out.println(name);
        this.age=age;
        this.name=name;
        System.out.println("有参参构造器");
    }

    public static void main(String[] args){
        ObjectInit a=new ObjectInit(25,"rose");
        ObjectInit b=new ObjectInit();
        System.out.println(a.name);
        System.out.println(b.name);


    }
}
