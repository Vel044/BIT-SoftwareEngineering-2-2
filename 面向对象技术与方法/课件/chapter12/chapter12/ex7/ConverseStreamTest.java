package ex7;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConverseStreamTest {

	public static void main(String[] args) {
		String filename = "a.txt";
		
		try( 
			FileOutputStream fos = new FileOutputStream(filename);
		){
			fos.write("北京".getBytes("UTF-8"));
			fos.write("北京".getBytes("UTF-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int ch;
		try(
			FileInputStream fis = new FileInputStream(filename);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		) {

			while((ch=isr.read())!=-1){
				System.out.print((char)ch);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
