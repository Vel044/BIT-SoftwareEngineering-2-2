package factorydemo1;

public class CircleFactory implements ShapeFactory{
    public Shape getShape(){
        return new Circle();

    }
}
