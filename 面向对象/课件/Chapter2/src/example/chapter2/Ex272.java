package example.chapter2;


import java.util.Scanner;

public class Ex272 {
	/**
	 * ����������������+-* /����֯��������
	 */
	public static void main(String[] args) {
		//���������
		System.out.println("�����������");
		Scanner scn = new Scanner(System.in);
		char operator=scn.next().charAt(0);   //��ȡ�����ַ����ĵ�һλ���õ�һ���ַ�
		
		//��������������
		System.out.println("����������������");
		double x=scn.nextDouble();
		double y=scn.nextDouble();
		double std = 0;
		int flag = 1; //1���Ϸ��������0���Ƿ������
		
		//�����ʽ
		if(operator=='+'){
			System.out.print(""+x+operator+y+"=");
			std = x+y;
		}else if(operator=='-'){
			System.out.print(""+x+operator+y+"=");
			std = x-y;
		}else if(operator=='*'){
			System.out.print(""+x+operator+y+"=");
			std = x*y;
		}else if(operator=='/'){
			System.out.print(""+x+operator+y+"=");
			std = x/y;
		}else{
			flag = 0;
		}
		
		if(flag==0){
			System.out.println("������+��-��*����/");
		}else{
			//�����
			double res = scn.nextDouble();
			//�ж϶Դ�
			if(Math.abs(res-std)<1e-6){
				System.out.println("����ȷ");
			}else{
				System.out.println("�ش����");
			}
		}
	}
}
