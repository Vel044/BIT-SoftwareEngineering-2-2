import java.util.function.Consumer;

public class o {

    public static void main(String[] args) {
        // 变量捕获
        int x = 10;
        MyFunction myFunction = y -> System.out.println(x + y);
        myFunction.doSomething(5); // 输出 15

        int x1 = 10;
        Consumer<Integer> m = y -> System.out.println(x1 + y);
        m.accept(5); // 输出 1
    }
}

interface MyFunction {
    void doSomething(int y);
}