package example.chapter2;

import java.util.Scanner;

public class Ex25 {

	/**
	 * 航班计算问题。设某航班周一、三、四、六飞行，当客户订票时如何根据客户的需求“星期几”获知该日是否有航班
	 */
	public static void main(String[] args) {
		byte  flagFight=90;  //1,3,4,6有航班

		//输入要查询的日期
		System.out.println("输入要查询的日期(星期几),星期日用0表示：");
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();   
		
		if( (flagFight&(1<<n))>0){
			System.out.println("该日有航班");
		}else{
			System.out.println("该日没有航班");
		}		
		
	}
}
