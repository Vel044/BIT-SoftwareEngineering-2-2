package example.chapter2;

public class Ex22 {

	public static void main(String[] args) {		
		int  i = 10, j = 8, m = 11, n = 20, k, g;    
			
		System.out.println(i++);		//�ҵ�����i����++���������ʲô
		System.out.println(++j);		//�ҵ�����i����++���������ʲô
			
		System.out.println("i="+i);	
		System.out.println("j="+j);
			
		k = m++;			//�ҵ�����m����++���������ʲô
		System.out.println("k="+k);	
		System.out.println("m="+m);
			
		g = 3*(++n);		//�ҵ�����n����++���������ʲô
		System.out.println("g="+g);
		System.out.println("n="+n);
	}

}
