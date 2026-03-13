import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestLambda {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "ZhangSan", 28, 98));
        employees.add(new Employee(2, "LiSi", 21, 100));
        employees.add(new Employee(3, "KangKang", 27, 89));
        employees.add(new Employee(4, "LiMing", 19, 92));
        employees.add(new Employee(5, "WangGang", 22, 66));
        employees.add(new Employee(6, "ZhaoXin", 24, 85));
        employees.add(new Employee(7, "LiuWei", 20, 78));
        employees.add(new Employee(8, "BaiZhanTang", 16, 99));

        System.out.println("°´ÕÕNameÅÅÐò£º");
        // °´NameÅÅÐò
        employees.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
        employees.forEach(System.out::println);

        System.out.println("°´ÕÕGradeÅÅÐò£º");

        // °´gradeµ¹ÐòÅÅÐò
        employees.sort((e1, e2) -> e1.getGrade() - (e2.getGrade()));
        employees.forEach(System.out::println);

        System.out.println("°´ÕÕAgeÅÅÐò£º");

        // °´ageÅÅÐò
        employees.sort((e1, e2) -> e1.getAge() - (e2.getAge()));

        // ´òÓ¡ÅÅÐòºóµÄ½á¹û
        employees.forEach(System.out::println);
    }
}

class Employee {
    private int id;
    private String name;
    private int age;
    private int grade;

    public Employee(int id, String name, int age, int grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                '}';
    }

}
