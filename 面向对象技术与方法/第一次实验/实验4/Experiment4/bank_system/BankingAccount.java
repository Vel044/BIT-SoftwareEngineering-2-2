public class BankingAccount extends Account {
    private int days;
    private double annualInterestRate;

    public BankingAccount(String idCard, double balance, int days, double annualInterestRate) {
        super(idCard, balance);
        this.days = days;
        this.annualInterestRate = annualInterestRate;
    }

    @Override
    public double getInterest() {
        return balance * annualInterestRate /100 * days / 365;
    }

    @Override
    public String toString() {
        return super.toString() + "存款的 " + days + "天的存款利息:" + getInterest() + "(年利率为" + annualInterestRate + "%)";
    }
}