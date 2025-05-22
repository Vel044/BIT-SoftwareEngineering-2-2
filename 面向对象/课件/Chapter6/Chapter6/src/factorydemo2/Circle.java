package factorydemo2;

//import factorydemo1.ShapeFactory;

public class Circle implements Shape {
    private Circle(){

    }

    public static ShapeFactory CF=new ShapeFactory(){
        public Shape getShape(){
           Shape s=new Circle();
           return s;
        }

    };
    public void draw() {
        System.out.println("this is a circle");
    }
}
