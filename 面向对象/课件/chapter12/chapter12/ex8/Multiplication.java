package ex8;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Multiplication {

	public static void main(String[] args) {

		try (
				FileWriter	fw = new FileWriter("aa.txt");
				FileReader fr = new FileReader("aa.txt");
		){
			int ch;
			for(int i=1; i<=9; i++){
				for(int j=1; j<=i; j++){
					//写入字符串
					fw.write(j+"*"+i+"="+(i*j)+"\t");
				}
				fw.write("\r\n");  //每行结束后输出一个回车换行
			}
			fw.flush();

			//读出并在控制台打印
			while( (ch=fr.read())!=-1){  //每次读取一行
				System.out.print((char)ch);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}