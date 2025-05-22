package factorydemo2;

public class FactoryDemo {
    public static void main(String[] args) {
        Circle a=(Circle) TestClass.haveShape(Circle.CF);
        a.draw();


}
}

