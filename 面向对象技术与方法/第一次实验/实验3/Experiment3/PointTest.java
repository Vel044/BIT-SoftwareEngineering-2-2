import java.lang.StringBuilder;
import java.util.Random;
import java.util.Scanner;




public class PointTest {

    public static void main(String[] args) {
        Point p1 = new Point(3, 4);
        Point p2 = new Point(5);
        Point p3 = new Point(1, 2);

        // 计算点到原点的距离
        System.out.println("p1到原点的距离：" + p1.distance());
        System.out.println("p2到原点的距离：" + p2.distance());
        System.out.println("p3到原点的距离：" + p3.distance());

        // 计算点到另外一个坐标的距离
        System.out.println("p1到坐标(0, 0)的距离：" + p1.distance(0, 0));
        System.out.println("p2到坐标(2, 2)的距离：" + p2.distance(2, 2));
        System.out.println("p3到坐标(5, 6)的距离：" + p3.distance(5, 6));

        // 计算点到另外一个点的距离
        System.out.println("p1到p2的距离：" + p1.distance(p2));
        System.out.println("p2到p3的距离：" + p2.distance(p3));
        System.out.println("p3到p1的距离：" + p3.distance(p1));
    }
}
