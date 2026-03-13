package example.chapter2;

import java.util.Scanner;
/**
 * �������һ��������1~100֮�䣩�����û����в�����ÿ�θ�����С����ʾ������¼�����Ĵ���
 */
public class Ex29{
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		//1.����һ�����µ�����
		int x = (int)(Math.random()*100+1);  //Math.random()�������[0,1)��ĸ�����
		System.out.println("���µ������ǣ�"+x);
		
		//2.��ʼ��ѭ������guessNumber�ͼ�����count
		System.out.println("������µ����֣�");		
		int guessNumber = scn.nextInt();
		int count = 1;	//�����Ĵ���
		
		//3.�����ж�
		while(guessNumber!=x){
			if(guessNumber<x){
				System.out.println("С��");	
			}else{
				System.out.println("����");	
			}
			System.out.print("������µ����֣�");		
			guessNumber=scn.nextInt();
			count++;
		}
		System.out.println("��ȷ������"+count+"��");	
		scn.close();
	}
}
