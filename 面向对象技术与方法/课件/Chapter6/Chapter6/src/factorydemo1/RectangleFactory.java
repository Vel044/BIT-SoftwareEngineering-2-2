package factorydemo1;

public class RectangleFactory implements ShapeFactory{
    public Shape getShape(){
        return new Rectangle();

    }
}
