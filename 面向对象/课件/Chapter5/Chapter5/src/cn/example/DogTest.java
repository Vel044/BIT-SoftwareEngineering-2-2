package cn.example;

class Pet{
    private String name;
   public Pet(){
        System.out.print(1);
    }
    public Pet(String name){
        System.out.print(2);
    }
}
class Dog extends Pet{
    public Dog(String name){
        System.out.print(3);
    }
}

public class DogTest {
    public static void main(String[] args){
        Dog a=new Dog("旺财");

    }
}
