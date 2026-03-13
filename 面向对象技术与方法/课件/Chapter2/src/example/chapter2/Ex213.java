package example.chapter2;

import java.util.Scanner;

public class Ex213 {

	/**
	 * ��ӡһ��ָ����С��n��n�����̣����Ǻű�ʾ�����λ�ã�����λ�õı����0~9��a~z���α�ʾ
	 */
	public static void main(String[] args) {	
		Scanner scn = new Scanner(System.in);
		 
		System.out.print("�������̵Ĵ�С:");
		int column = scn.nextInt();  //��ӡ������������		   
		
		//�����һ��̧ͷ
		System.out.print("\t");
		for(int i=0; i<column; i++){
			if (i<10){ //������ֱ�ʾ�к�
				System.out.print(i+"\t");  
			}else{  //��������ĸa,b...��ʾ�к�
				System.out.print((char)('a'+i-10)+"\t");  
			}
		}
		System.out.println();		
		
		//�������
		for (int i=0; i<column; i++){
		    //����к�
			if (i<10){
				System.out.print(i+"\t");
			}else{
				System.out.print((char)('a'+i-10)+"\t");
			}
			
			//����Ǻ�
			for (int j=1; j<=column; j++)
				System.out.print("*\t");
			
			System.out.println();
		}
	}

}
