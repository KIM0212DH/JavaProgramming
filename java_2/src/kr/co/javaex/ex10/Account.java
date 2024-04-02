package kr.co.javaex.ex10;

public class Account {
    static final int MIN_BALANCE = 0;
    static final int MAX_BALANCE = 1000000;
    private int balance;

    public Account() {
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        if (balance >= 0 && balance <= 1000000) {
            this.balance = balance;
        }
    }
}
