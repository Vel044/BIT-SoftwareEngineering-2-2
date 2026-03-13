package cn.edu.bit;

public class StaticInit {
    static int i=6;//静态数据成员的指定初始化，是一种假象，编译时会成为静态块中的一条赋
    static String s;
    static{
        //i=6;
        System.out.println(s);
        s="hello world";
        System.out.println(s);
        System.out.println(i);
        i=20;
        System.out.println(i);;
        //age=30;

    }
    static int age=30;
    public static void main(String[] args){
        StaticInit e;//定义变量，不需要加载类
        //.out.println(i);//使用类成员，需要加载类，且只加载一次
        //System.out.println(i);
        //System.out.println(age);

    }
}
