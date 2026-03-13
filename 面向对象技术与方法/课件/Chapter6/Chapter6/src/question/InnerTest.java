package question;

public class InnerTest {
    static  class Father{
        public int money=1;
        Father(){
            money=2;
            showMoney();
        }
        public void showMoney(){
            System.out.println("father has"+this.money);

        }
    }

    static  class Son extends Father{
        public int money=3;
        Son(){
            money=4;
            showMoney();
        }
        public void showMoney(){
            System.out.println("son has"+money);

        }

    }

    public static void main(String[] args){
        Father a=new Son();
        System.out.println("father a has"+a.money);

    }
}
