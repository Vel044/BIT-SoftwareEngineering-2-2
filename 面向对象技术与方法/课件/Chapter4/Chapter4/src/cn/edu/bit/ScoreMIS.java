package cn.edu.bit;

import java.util.Scanner;

/***
 * ѧ���ɼ���ѯϵͳ
 */
public class ScoreMIS {
	static final int STUDENT_NUM=6;
	static final int COURSE_NUM=5;
		
	static String[] students = {"zhang","wang","li","zhao","liu","song"};
	static String[] courses = {"C","Java","mySQL","Linux","HTML"};
	static int[][] score= new int[STUDENT_NUM][COURSE_NUM];
	
	public static void main(String[] args)	{		
		initScore();	//��ʼ��ѧ���ɼ�
	 	show();         //��ʾ�γ̡�ѧ�����ɼ�
	 	run();          //��ʼ���տ���̨����
	}
	
	//��Ӧ����̨����
	public static void run(){
		Scanner scn=new Scanner(System.in);		
		
		while(true){
			System.out.print("����������:");	
			String command=scn.next();
			
			if(command.equalsIgnoreCase("avg")){//"avg"������Ҫһ������
				String parameter=scn.next();
				avg(parameter);
			}else if(command.equalsIgnoreCase("get")){//"get"������Ҫ��������
				String parameter1=scn.next();
				String parameter2=scn.next();
				get(parameter1,parameter2);
			}else if(command.equalsIgnoreCase("sort")){//"sort"������Ҫһ������
				String parameter=scn.next();		
				sortByScore(parameter);
			}else if (command.equalsIgnoreCase("exit")){//�˳���ѯϵͳ
				System.out.println("�˳���ѯϵͳ��byebye��");
				System.exit(0);
			}
		}		
	}
	
	//���������ʼ���ɼ�
	public static void initScore(){  		
		for(int i=0; i<score.length; i++)
			for(int j=0; j<score[i].length; j++)
				score[i][j]=(int)(Math.random()*101);
	}

	//��ʾ�ɼ�
	public static void show(){  		
		System.out.print("\t");  //������ʾ������λ��
		//����γ�����
		for(int i=0; i<courses.length; i++){
			System.out.print(courses[i]+"\t");
		}
		System.out.println();
		
		for(int i=0; i<score.length; i++){
			//��ʾѧ������
			System.out.print(students[i]+"\t");
			//��ʾ��ѧ���ɼ�
			for(int j=0; j<score[i].length; j++){
				System.out.print(score[i][j]+"\t");
			}
			System.out.println();
	  }
	}
	
	//condition1:ѧ������
	//condition2:�γ�����
	public static void get(String condition1, String condition2){
		int i_index=-1, j_index=-1;

		//�����Ƿ���ڸ�ѧ��i_index
		for(int i=0; i<students.length; i++){
			if (students[i].equalsIgnoreCase(condition1)){
				i_index=i;
			}
		}
		if(i_index!=-1){  //�д��ˣ����������Ƿ��д˿γ�j_index
			for(int j=0; j<courses.length; j++)
				if (courses[j].equalsIgnoreCase(condition2)){
					j_index=j;					
				}
		}else{
			System.out.println("û�� "+ condition1+ " �����");
			return;
		}
	
		if(j_index!=-1){
			System.out.println(condition1+"��"+condition2+"�ĳɼ��ǣ�"+score[i_index][j_index]);
		}else{
			System.out.println(condition1+"û�� "+condition2+" ���ſγ�");
			return;
		}
	}
	
	//condition:��ƽ���ֵĲ�����ѧ������/�γ����ƣ�
	public static void avg(String condition){
		int i_index=-1, j_index=-1;
		
		//�����Ƿ���ѧ������
		for(int i=0;i<students.length; i++){
			if (students[i].equalsIgnoreCase(condition)){
				i_index=i;
			}
		}
		if(i_index!=-1){//������ѧ������
			double sum=0;
			
			//��ѧ����ƽ����
			for(int j=0; j<score[i_index].length; j++){
				sum=sum+score[i_index][j];
			}
			System.out.printf("%s��ƽ�����ǣ�%.2f\n",condition,sum/COURSE_NUM);
		}else{
			//���Ҳ����Ƿ��ǿγ�
			for(int j=0; j<courses.length; j++){
				if (courses[j].equalsIgnoreCase(condition)){
					j_index=j;
				}
			}
			if (j_index!=-1){  //�����ǿγ���
				double sum=0;
			    for(int i=0; i<score.length; i++){
					 sum=sum+score[i][j_index];
				}
				System.out.printf("%s��ƽ�����ǣ�%.2f\n",condition,sum/STUDENT_NUM);
			}else{
					System.out.println("������ļȲ��ǿγ�����Ҳ����ѧ����");
			}			
		}
	}
	
	//sort_condition:����γ�����
	public static void sortByScore(String sort_condition){
		int i, j;
		int j_index=-1;

		for(j=0; j<courses.length; j++){
			if (courses[j].equalsIgnoreCase(sort_condition)){
				j_index=j;
			}
		}
		if(j_index==-1){
			System.out.println("û�����ſγ�");
			return;
		}
		
		//���ɼ����ݵ�����ʱ������		
		int[] score_temp= new int[STUDENT_NUM]; 
		for(i=0; i<score.length; i++){
			score_temp[i]=score[i][j_index];
		}
		//���������뵽��ʱ������
		String[] students_temp=new String[STUDENT_NUM];
		for(i=0; i<students.length; i++){
			students_temp[i]=students[i];
		}
					
		//��score_temp����ð�ݷ�����ͬʱ��student_temp���ݽ���ͬ������
		for(int k=0; k<score_temp.length-1; k++){
			for (int kk=0; kk<score_temp.length-k-1; kk++){
				if (score_temp[kk]>score_temp[kk+1]){
					//�����ɼ�
					int temp=score_temp[kk];
					score_temp[kk]=score_temp[kk+1];
					score_temp[kk+1]=temp;
					
					//�����ɼ���ͬʱ��������
					String tmp_str=students_temp[kk];  
					students_temp[kk]=students_temp[kk+1];
					students_temp[kk+1]=tmp_str;
				}
			}
		}
		
		//���������
		//1.���̧ͷ
		System.out.print("����\t");
		System.out.print("����\t");
		System.out.print(courses[j_index]+"\t");
		System.out.println();
		
		//2.�������
		for(int k=0; k<score_temp.length; k++){
			System.out.print((k+1)+"\t");
			System.out.print(students_temp[k]+"\t");  //ѧ������
			System.out.println(score_temp[k]);  //ѧ���ɼ�
		}
	}
}
