package ex5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStreamTest {
	public static void main(String[] args) {

		try(
			FileOutputStream fos = new FileOutputStream("object.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);	
		) {
			Student stu = new Student("Lucy", 15);
			System.out.println(stu.getName()+","+stu.getAge());

			oos.writeObject(stu);

			stu.setName("jack");
			stu.setAge(20);
			oos.writeObject(stu);
			oos.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try(
			FileInputStream fis = new FileInputStream("object.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
		) {			
			Student stuR1=(Student)ois.readObject();
			Student stuR2=(Student)ois.readObject();
			System.out.println(stuR1==stuR2);
			System.out.println(stuR1.getName()+","+stuR1.getAge());
			System.out.println(stuR1);
			System.out.println(stuR2.getName()+","+stuR2.getAge());
			System.out.println(stuR2);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
