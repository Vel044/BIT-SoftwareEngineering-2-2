class Animal {
    int age;
    String name;
    void move(){System.out.print("i can move");}
}

class Bird extends Animal {

    //void move(){System.out.print("i can fly");}
    void sing(){System.out.print("i can sing");}
}
public class InstanceofTest {


    public static void main(String[] args){
        Animal animal=new Animal();
        Animal birdA=new Bird();
        Bird peggie=new Bird();
       // Bird b=(Bird)animal;
       // b.sing();
        String s="i am a bird";
        //if (s instanceof Bird )           s.sing(); //s 必须属于Bird继承体系
       /* if (b instanceof Bird )
            b.sing();*/
        if (birdA instanceof Bird ) {
            Bird birdB =(Bird)birdA;
            birdB.sing();
        }



        if (birdA instanceof Bird birdB) {
            birdB.sing();
        }
        else{
           // System.out.print("no"+birdB); //birdB没有被创建
        }




        }




}
