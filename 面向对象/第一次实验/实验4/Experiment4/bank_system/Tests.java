public class Tests {
    public static void main(String[] args) {
        FixedDepositAccount account1 = new FixedDepositAccount("001", 100000.00, 36, 3.50);
        BankingAccount account2 = new BankingAccount("002", 100000.00, 182, 5.20);

        System.out.println(account1.toString());
        System.out.println(account2.toString());
    }
}
