package kr.co.javaex.java_22_99_ex2_Account;

public class AccountEx {
    public static void main(String[] args) {
        Account a = new Account("4649",1592);
        System.out.println(a);

        Account b = new Account(" 4649 ",1592);
        if (a.equals(b)){
            System.out.println("같은 계좌");
        }
        else {
            System.out.println("다른 계좌");
        }

    }
}
