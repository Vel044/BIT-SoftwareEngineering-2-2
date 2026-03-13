public class Account {
    protected String idCard;
    protected double balance;

    public Account(String idCard, double balance) {
        this.idCard = idCard;
        this.balance = balance;
    }

    public double getInterest() {
        return 0; // 默认利息为0
    }

    @Override
    public String toString() {
        return idCard + " 账户 " + balance + "元";
    }
}