package ex4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SavePrintPrimeNumber {
	
	public static void main(String[] args) {
		
		try(
			FileOutputStream fos = new FileOutputStream("num.dat");
			DataOutputStream dos = new DataOutputStream(fos);
		){
			for(int n=1000; n<2000; n++)
				if(isPrime(n))
					dos.writeInt(n);  
		}catch(IOException e){
			e.printStackTrace();
		}
		
		int count=0; 
		try(
			FileInputStream fis = new FileInputStream("num.dat");
			DataInputStream dis = new DataInputStream(fis);
		){
			while(dis.available()>0) {
				count++;
				System.out.print( dis.readInt() +"   ");
				if(count%10==0)
					System.out.println();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private static boolean isPrime(int x){
		for(int div=2; div<=Math.sqrt(x); div++)
			if(x%div==0)
				return false;
		return true;
	}
}
