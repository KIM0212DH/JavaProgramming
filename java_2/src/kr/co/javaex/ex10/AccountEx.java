package kr.co.javaex.ex10;

public class AccountEx {
    public AccountEx() {
    }

    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(10000);
        System.out.println(account.getBalance());
        account.setBalance(-100);
        System.out.println(account.getBalance());
        account.setBalance(200000);
        System.out.println(account.getBalance());
        account.setBalance(300000);
        System.out.println(account.getBalance());
    }
}
