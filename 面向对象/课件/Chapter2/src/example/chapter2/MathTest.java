package example.chapter2;

import java.util.Scanner;

public class MathTest {

	public static void main(String[] args) {
		//��ʾ�˵�
		System.out.println("***�밴���ѡ��ʹ���ĸ�����***");
		System.out.println("1.�ж�ĳ���Ƿ�Ϊ����");
		System.out.println("2.��ȡ������");
		System.out.println("3.������ϰ��");
		System.out.println("0.�˳�");
		
		run();
	}
	
	public static void run(){
		Scanner scn = new Scanner(System.in);
		 
		System.out.print("����˵����:");
		int option = scn.nextInt();  		
		int x;
		
		while(option!=0){
			switch(option){
			case 1://����
				System.out.println("������һ�����֣�");
				x = scn.nextInt();
				if(isPrime(x)){
					System.out.println(x+"������");
				}else{
					System.out.println(x+"��������");
				}
				break;
			case 2: //������
				System.out.println("���������ڵ���������");
				x = scn.nextInt();
				
				int count = getIntimacy(x);
				if(count==0){
					System.out.println("�÷�Χ��û��������");
				}else{
					System.out.println("����������"+count+"��");
				}
				break;
			case 3:  //������ϰ��
				System.out.println("����Ҫ��ϰ��Ŀ�ĸ�����");
				x = scn.nextInt();
				excercise(x);
			}//switch end
			System.out.print("����˵����:");
			option = scn.nextInt();  	
		}
		System.out.println("�ټ�!");		
	}

	public static boolean isPrime(int x){
		for(int div=2; div<=Math.sqrt(x); div++){
			if(x%div==0){
				return false;
			}
		}
		return true;
	}
	
	public static int getIntimacy(int n){		
		int a,b,count=0,sumDivB=0; 
		
		for(a=1; a<n; a++){ //������֮һ��a
			b=1;   //������֮����b�� a������֮�ͣ�
			for(int i=2; i<=Math.sqrt(a); i++){  //�����ڸ���a��Χ��
				if(a%i==0){
					b=b+i+a/i;  //i��a/iͬʱ����a������					
				}
			}
			
			if(a<b){  //ֻ���a<b�����
				sumDivB=1; //sumDivB��b������֮��
				for(int i=2; i<=Math.sqrt(b); i++){ 
					if(b%i==0){
						sumDivB=sumDivB+i+b/i;
					}
				}
			}
			if(sumDivB==a){//b������֮��sumDivB��a���
				System.out.println(a+"��"+b+"��һ��������");
				count++;
			}
		}
		return count;
	}
	public static void excercise(int count){//������ϰ
		int m,n,op,resInput,resCalculate = 0 ;
		int countr=0;    //������ȷ������
		int countw=0;	 //������������
		
		do{
			do{//��ȡ�������������(��λ��)
				m=(int)(Math.random()*100);
				n=(int)(Math.random()*100);
			}while(m<10 || n<10);
			
			//����õ�һ�������0~3,0:�ӷ�;1:����; 2:�˷�; 3:����
			op=(int)(Math.random()*4);   
			
			switch(op){
			case 0: System.out.println(m+"+"+n+"="); resCalculate=m+n; break;
			case 1: System.out.println(m+"-"+n+"="); resCalculate =m-n; break;
			case 2: System.out.println(m+"*"+n+"="); resCalculate =m*n; break;
			case 3: System.out.println(m+"/"+n+"="); resCalculate =m/n;		
			}
			
			Scanner sc=new Scanner(System.in);
			resInput=sc.nextInt();  //�û�����Ĵ�
			
			if(resInput == resCalculate){
				System.out.println("����ȷ!");
				countr++;
			}else {
				System.out.println("�𰸴���!");
				countw++;
			}
		}while((countr+countw)<count); 

		System.out.print("������" +countr+"���⣡");
		System.out.println("����" +countw+"���⣡");
	}	
}
