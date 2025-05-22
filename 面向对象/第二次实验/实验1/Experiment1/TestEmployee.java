import java.util.Arrays;
import java.util.Comparator;

public class TestEmployee {
    public static void main(String[] args) {
        Employee[] employees = {
                new Employee(1, "ZhangSan", 28, 98),
                new Employee(2, "LiSi", 21, 100),
                new Employee(3, "KangKang", 27, 89),
                new Employee(4, "LiMing", 19, 92),
                new Employee(5, "WangGang", 22, 66),
                new Employee(6, "ZhaoXin", 24, 85),
                new Employee(7, "LiuWei", 20, 78),
                new Employee(8, "BaiZhanTang", 16, 99)
        };

        // 排序
        Arrays.sort(employees);

        // 打印排序结果
        // 打印排序结果
        for (Employee emp : employees) {
            System.out.println(emp.toString());
        }


    }
}
