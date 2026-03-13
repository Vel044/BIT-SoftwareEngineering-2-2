package factorydemo2;

public class TestClass {
    public static Shape haveShape(ShapeFactory fact){
        Shape a= fact.getShape();
        return a;
    }
}
