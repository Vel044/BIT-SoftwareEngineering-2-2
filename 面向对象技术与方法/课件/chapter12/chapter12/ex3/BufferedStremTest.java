package ex3;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class BufferedStremTest {
	public static void main(String[] args) {
		try( 
				FileInputStream fis = new FileInputStream("a.jpg");
				FileOutputStream fos = new FileOutputStream("b.jpg");
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
		){
			int data;			
			Date d1 = new Date();
			while((data=bis.read())!=-1){
				bos.write(data);
			}
			Date d2 = new Date();			
			System.out.println("buffer"+(d2.getTime()-d1.getTime()));
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
