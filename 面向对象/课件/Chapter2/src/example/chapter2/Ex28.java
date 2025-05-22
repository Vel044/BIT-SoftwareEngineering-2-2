package example.chapter2;

import java.util.Scanner;

public class Ex28 {

	/**
	 * ��ѧ����
	 */
	public static void main(String[] args) {
		System.out.println("����x��");
		Scanner scn = new Scanner(System.in);
		int x=scn.nextInt(); 
		int y;
		
		if(x<0){  //ȥ��������ɢΪ�������ֵķ�֧
	      	y=0;
	    }else{
	    	switch(x/10){
	    		case 0: y=x; break;
	    		case 1:
	    		case 2: y=x+10; break;   //case 2��case 1ʹ����ͬ�Ĵ������
	    		case 3: y=-x; break;
	    		default: y=-x-10;       //�ų��������������
	    	}
	    	System.out.println("f(x)="+y);
	    }
	}
}
