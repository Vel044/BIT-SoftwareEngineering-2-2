package example.chapter2;

import java.util.Scanner;

public class Ex212 {

	/**
	 * �ж�ĳ�����Ƿ�������
	 */
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);		

		System.out.print("����������������:");
		int x=scn.nextInt();
		int div;
		
		for(div=2; div<=Math.sqrt(x); div++){
			if(x%div==0){//����������div<=Math.sqrt(x)
				break;
			}
		}
		if(div>Math.sqrt(x)){//ȫ������ɨ����δ����
			System.out.println(x+"������");
		}else{
			System.out.println(x+"��������");
		}
	}

}
