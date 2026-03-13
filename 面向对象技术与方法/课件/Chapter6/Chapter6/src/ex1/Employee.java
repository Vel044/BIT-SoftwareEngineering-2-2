package ex1;

public class Employee {

	private String id;
	private String name;

	public Employee() {
	}

	public Employee(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if (obj instanceof Employee) {  //判断是否能向下转型
			Employee e = (Employee) obj;
			return e.id.equals(this.id) && e.name.equals(this.name);
		}
		return false;
	}

	public static void main(String[] args){
		Employee e1 = new Employee("001","zhang");
		Employee e2 = e1;
		Employee e3 = new Employee("001","zhang");

		System.out.println(e1.equals(e2));	//true
		System.out.println(e1.equals(e3));	//重写前false，重写后true
	}

}