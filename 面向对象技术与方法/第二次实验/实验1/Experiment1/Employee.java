public class Employee implements Comparable<Employee> {
    public int id;
    public String name;
    public int age;
    public double salary;

    // 构造方法
    public Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    // 实现compareTo方法，按照姓名升序排序
    @Override
    public int compareTo(Employee other) {
        // 首先比较工资
        int salaryComparison = Double.compare(this.salary, other.salary);
        if (salaryComparison != 0) {
            return salaryComparison;
        }
        // 如果工资相同，再比较姓名
        return this.name.compareTo(other.name);
    }

    // 可以根据需要实现其他比较规则，比如按照工资排序
    // 重写toString方法，用于打印Employee对象信息
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
