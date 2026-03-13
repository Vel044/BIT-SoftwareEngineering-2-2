package example.chapter2;

import java.util.Scanner;
/**
 * 判断某数是否是素数的方法
 */
public class Ex214 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		 
		System.out.print("输入一个整数,判断其是否为素数:");
		int n = scn.nextInt();	
		
		if( isPrime(n)){
			System.out.println(n+"是素数");
		}else{
			System.out.println(n+"不是素数");
		}
	}
	
	public static boolean isPrime(int x){
		for(int div=2; div<=Math.sqrt(x); div++){
			if(x%div==0){
				return false;
			}
		}
		return true;
	}
}
