package kr.co.javaex.ex09.java_09_2_ex1_accountManager;

public class Account {
    private String accountNum;
    private String name;
    private int income;

    public Account() {
    }

    public String getAccountNum() {
        return this.accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIncome() {
        return this.income;
    }

    public void setIncome(int income) {
        this.income = income;
    }
}
