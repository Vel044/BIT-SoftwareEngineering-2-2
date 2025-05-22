package example.chapter2;


import java.util.Scanner;

public class Ex27If {
	/**
	 * 根据输入的运算符（+-* /）组织各种运算
	 */
	public static void main(String[] args) {
		//输入运算符
		System.out.println("输入运算符：");
		Scanner scn = new Scanner(System.in);
		char operator=scn.next().charAt(0);   //获取键入字符串的第一位，得到一个字符

		//输入两个运算数
		System.out.println("输入两个运算数：");
		double x=scn.nextDouble();
		double y=scn.nextDouble();
		//输出算式
		if (operator=='+') {
			System.out.print("" + x + operator + y + "=");
		}else if (operator=='-') {
			System.out.print("" + x + operator + y + "=");
		}else if (operator=='*') {
			System.out.print("" + x + operator + y + "=");
		}else if (operator=='/') {
			System.out.print("" + x + operator + y + "=");
		}else {
			System.out.println("请输入+、-、*或者/");
		}
	}
}