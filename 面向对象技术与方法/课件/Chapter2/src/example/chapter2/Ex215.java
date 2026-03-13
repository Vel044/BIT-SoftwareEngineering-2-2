package example.chapter2;

public class Ex215 {
	/**
	 * ������
	 */
	public static void main(String[] args) {
		//printPyramid(7);
		printPyramid(9);
		printPyramid('g');
	}
	
	public static void printPyramid(int n){ //��ӡn��������ɵĽ�����
		for(int i=1; i<=n; i++){
			for(int j=1; j<=n-i; j++){
				System.out.print(" ");//
			}
			for(int j=1; j<=i; j++){
				System.out.print(j);
			}
			for(int j=i-1; j>=1; j--){
				System.out.print(j);
			}
			System.out.println();			
		}		
	}
	
	public static void printPyramid(char c){ //��ӡn���ַ���ɵĽ�����
		for(char row='a'; row<=c; row++){
			//�ո�
			for(int i=1; i<=c-row; i++){
				System.out.print(" ");
			}
			//���򲿷�
			for(char ch='a'; ch<=row; ch++){
				System.out.print(ch);
			}
			//���򲿷�
			for(char ch=(char)(row-1); ch>='a'; ch--){
				System.out.print(ch);
			}
			System.out.println();
		}

	}

}
