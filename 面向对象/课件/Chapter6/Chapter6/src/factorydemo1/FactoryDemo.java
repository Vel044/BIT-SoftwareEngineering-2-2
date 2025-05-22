package factorydemo1;

public class FactoryDemo {
    public static void main(String[] args) {
        ShapeFactory CF=new CircleFactory();
        Circle a=(Circle)CF.getShape();
        a.draw();
        ShapeFactory RF=new RectangleFactory();
        Rectangle b=(Rectangle)RF.getShape();
        b.draw();
        ShapeFactory SF=new SquareFactory();
        Square c=(Square)SF.getShape();
        c.draw();
    }
}
