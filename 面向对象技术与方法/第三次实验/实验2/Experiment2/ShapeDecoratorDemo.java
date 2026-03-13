interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    public void draw() {
        decoratedShape.draw();
    }
}

class ColorShapeDecorator extends ShapeDecorator {
    private String color;

    public ColorShapeDecorator(Shape decoratedShape, String color) {
        super(decoratedShape);
        this.color = color;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        System.out.println("Coloring the shape with " + color);
    }
}

public class ShapeDecoratorDemo {
    public static void main(String[] args) {
        // 创建一个长方形
        Shape rectangle = new Rectangle();

        // 用红色色着
        Shape redRectangle = new ColorShapeDecorator(rectangle, "Red");

        // 绘制长方形并用红色着色
        redRectangle.draw();
    }
}
