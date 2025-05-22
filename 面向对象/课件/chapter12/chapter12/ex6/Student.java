package ex6;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Student implements Serializable{

	private static final long serialVersionUID = -5079927634365312280L;
	private String name;
	private int age;
	//transient private Teacher teacher;
	//如果Teacher没有实现serializable接口，使用下句会抛出异常
	private Teacher teacher;
	public Student deepClone() throws ClassNotFoundException, IOException{
		//将对象写到流里
		ByteArrayOutputStream bos = new ByteArrayOutputStream();


		ObjectOutputStream oos = new ObjectOutputStream(bos);   //包装字节数组流
		oos.writeObject(this);

		//从流里读出对象
		//初始化字节数组输入流，大小及内容由字节数组输出流决定
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return((Student)ois.readObject());   //可能抛出ClassNotFoundException异常
	}

	public static void main(String[] args) throws ClassNotFoundException,IOException{
		Teacher teacher = new Teacher("LeoKang");
		Student s1 = new Student("Lucy",15,teacher);
		Student s2 = (Student)s1.deepClone();   //调用深复制方法
		s2.teacher.setName("GraceZhang");	//修改学生2的老师
		System.out.println("s1-teacher-name:"+s1.teacher.getName()); //学生1的老师不变
		System.out.println("s2-teacher-name:"+s2.teacher.getName());
	}


	public Student(String name, int age, Teacher teacher) {
		this.name = name;
		this.age = age;
		this.teacher = teacher;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}



}