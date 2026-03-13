package example.chapter2;

import java.util.Scanner;

public class Ex27Switch {
	/**
	 * 根据输入的运算符（+-* /）组织各种运算
	 */
	public static void main(String[] args) {

		// 输入运算符
		System.out.println("输入运算符：");
		Scanner scn = new Scanner(System.in);
		String operator = scn.next();

		// 输入两个运算数
		System.out.println("输入两个运算数：");
		double x = scn.nextDouble();
		double y = scn.nextDouble();

		switch (operator) {
			case "+":
				System.out.println(x + operator + y + "=");
				break;
			case "-":
				System.out.println(x + operator + y + "=");
				break;
			case "*":
				System.out.println(x + operator + y + "=");
				break;
			case "/":
				System.out.println(x + operator + y + "=");
				break;
			default:
				System.out.println("请输入+、-、*或者/");
		}
	}
}