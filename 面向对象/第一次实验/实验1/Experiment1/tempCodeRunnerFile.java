import java.util.Scanner;

public class Experiment1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            int a = (int) (Math.random() * 99);
            int b = (int) (Math.random() * 99);
            System.out.print(a + "+" + b + "=");
            if (scan.hasNextInt()) {
                int num = scan.nextInt();
                if (num == a + b) {
                    System.out.println("回答正确");
                } else {
                    System.out.println("回答错误");
                }
            }

            System.out.print("是否继续(Y/N):");
            if (scan.hasNext()) {
                String answer = scan.next();
                if (answer.equalsIgnoreCase("N")) {
                    break;
                }
            }
        }
        scan.close();
    }
}
