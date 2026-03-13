package cn.edu.bit;

public class DefaultConstructor {
    public static void main(String[] args){

        TestDefaultConstructor testCon=new TestDefaultConstructor();
        int iVal=testCon.getI();
        double bVal=testCon.getB();
        String sVal=testCon.getS();
    }

}

class TestDefaultConstructor{
    int i;
    double b;
    String s;

  /* TestDefaultConstructor(int i,double b,String s){
        this.i=i;
        this.b=b;
        this.s=s;
    }*/
    int getI(){System.out.println("the value of i is"+i );
        return i;
    }
    double getB(){System.out.println("the value of b is"+b );
        return b;
    }

    String getS(){System.out.println("the value of s is"+s);
        return s;

    }


}