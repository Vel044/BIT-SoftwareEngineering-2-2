package factorydemo1;

public class SquareFactory implements ShapeFactory{
    public Shape getShape(){
        return new Square();

    }
}
