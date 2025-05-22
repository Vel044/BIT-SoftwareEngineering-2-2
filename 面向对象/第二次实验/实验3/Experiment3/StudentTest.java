// 定义接口

interface TableOutput {
    void outputTable(String[][] data);
}

// 实现二维字符串数组存储方式
class StringArrayTableOutput implements TableOutput {
    @Override
    public void outputTable(String[][] data) {
        // 输出表头
        System.out.println("ID\tNAME\tGENDER\tAGE");
        // 输出每行数据
        for (String[] row : data) {
            for (String cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }
}

// 学生类
class Student {
    String id;
    String name;
    String gender;
    int age;

    public Student(String id, String name, String gender, int age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
}

// 实现Student类型数组存储方式
class StudentArrayTableOutput implements TableOutput {
    @Override
    public void outputTable(String[][] data) {
        // 输出表头
        System.out.println("ID\tNAME\tGENDER\tAGE");
        // 输出每个学生的数据
        for (String[] row : data) {
            Student student = new Student(row[0], row[1], row[2], Integer.parseInt(row[3]));
            System.out.println(student.id + "\t" + student.name + "\t" + student.gender + "\t" + student.age);
        }
    }
}

public class StudentTest {
    public static void main(String[] args) {
        // 学生数据存储在二维字符串数组中
        String[][] studentData = {
            {"1001", "zhangs", "男", "21"},
            {"1002", "lis", "男", "23"},
            {"1003", "wangwu", "女", "21"},
            {"1004", "zhangs", "男", "24"},
            {"1005", "zhaol", "女", "25"},
            {"1006", "qingqi", "男", "21"}
        };

        // 实例化两种存储方式的类
        TableOutput stringArrayOutput = new StringArrayTableOutput();
        TableOutput studentArrayOutput = new StudentArrayTableOutput();

        // 输出二维字符串数组存储方式的表格
        stringArrayOutput.outputTable(studentData);

        // 输出Student类型数组存储方式的表格
        studentArrayOutput.outputTable(studentData);
    }
}
