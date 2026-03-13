public class FixedDepositAccount extends Account {
    private int months;
    private double annualInterestRate;

    public FixedDepositAccount(String idCard, double balance, int months, double annualInterestRate) {
        super(idCard, balance);
        this.months = months;
        this.annualInterestRate = annualInterestRate;
    }

    @Override
    public double getInterest() {
        return balance * annualInterestRate * months / 12;
    }

    @Override
    public String toString() {
        return super.toString() + "存款的 " + months + "月存款利息:" + getInterest() + "(年利率为" + annualInterestRate + "%)";
    }
}
