package example.chapter2;

import java.util.Scanner;

public class Ex211 {

	/**
	 * ��������������������ŷ������㷨��շת������������ǵ����Լ��
	 */
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		int m,n,r;
		do{
			System.out.print("����������������:");
			m=scn.nextInt();
			n=scn.nextInt();
		}while(m<=0||n<=0);
		    
		r=m%n;    //ѭ��ǰ��ʼ������һ�μ�������
		while(r!=0){
			m=n;
			n=r;
		    r=m%n;
		}
		System.out.println("���ǵ����Լ����"+n);   //ѭ��������ĳ���Ϊ���Լ��
	}
}
