package kr.co.javaex.java_22_99_ex2_Account;

public class Account {
    String accountNumber;
    int balance;

    Account(String accountNumber, int balance){
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return this.balance + "원 (계좌번호="+this.accountNumber+")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj instanceof Account){
            Account account = (Account) obj;
            if (this.accountNumber.trim().equals(account.accountNumber.trim())){
                return true;
            }
        }
        return false;
    }
}
