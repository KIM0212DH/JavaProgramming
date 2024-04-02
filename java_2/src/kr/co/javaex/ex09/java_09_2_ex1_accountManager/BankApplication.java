package kr.co.javaex.ex09.java_09_2_ex1_accountManager;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankApplication {
    public BankApplication() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account[] list = new Account[100];
        BankApplication bankApplication = new BankApplication();
        boolean keep = true;

        while(keep) {
            System.out.println("-------------------------------------------------------");
            System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료");
            System.out.println("-------------------------------------------------------");
            System.out.print("선택>");

            int menu;
            try {
                menu = scanner.nextInt();
            } catch (InputMismatchException var7) {
                scanner.next();
                System.out.println("숫자를 입력해 주세요.");
                continue;
            }

            switch (menu) {
                case 1:
                    bankApplication.createAccount(list);
                    break;
                case 2:
                    bankApplication.showList(list);
                    break;
                case 3:
                    bankApplication.deposit(list);
                    break;
                case 4:
                    bankApplication.withdraw(list);
                    break;
                case 5:
                    keep = false;
                    System.out.println("프로그램 종료");
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }

    }

    public void createAccount(Account[] list) {
        System.out.println("----------");
        System.out.println("계좌생성");
        System.out.println("----------");
        Scanner createScanner = new Scanner(System.in);
        System.out.print("계좌번호: ");
        boolean createFail = false;
        String accountNum = createScanner.next();
        Account[] var5 = list;
        int income = list.length;

        for(int var7 = 0; var7 < income; ++var7) {
            Account account = var5[var7];
            if (account != null && account.getAccountNum().equals(accountNum)) {
                createFail = true;
                break;
            }
        }

        if (!createFail) {
            System.out.print("계좌주: ");
            String name = createScanner.next();

            while(true) {
                try {
                    System.out.print("초기입금액: ");
                    income = createScanner.nextInt();
                    break;
                } catch (InputMismatchException var9) {
                    System.out.println("숫자를 입력해 주세요.");
                    createScanner.next();
                }
            }

            Account newAccount = new Account();
            newAccount.setAccountNum(accountNum);
            newAccount.setName(name);
            newAccount.setIncome(income);

            for(int i = 0; i < list.length; ++i) {
                if (list[i] == null) {
                    list[i] = newAccount;
                    break;
                }
            }
        } else {
            System.out.println("이미 존재하는 계좌번호입니다.");
        }

    }

    public void showList(Account[] list) {
        System.out.println("----------");
        System.out.println("계좌목록");
        System.out.println("----------");
        Account[] var2 = list;
        int var3 = list.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Account show = var2[var4];
            if (show != null) {
                PrintStream var10000 = System.out;
                String var10001 = show.getAccountNum();
                var10000.println(var10001 + "\t" + show.getName() + "\t" + show.getIncome());
            }
        }

        System.out.println("-------------------------------------------------------");
    }

    public void deposit(Account[] list) {
        System.out.println("----------");
        System.out.println("예금");
        System.out.println("----------");
        Scanner depositScanner = new Scanner(System.in);
        boolean depositFail = true;
        System.out.print("계좌번호: ");
        String depositAccount = depositScanner.next();

        int idx;
        for(idx = 0; idx < list.length; ++idx) {
            if (list[idx] != null && list[idx].getAccountNum().equals(depositAccount)) {
                depositFail = false;
                break;
            }
        }

        if (!depositFail) {
            int depositAmount;
            while(true) {
                try {
                    System.out.print("예금액: ");
                    depositAmount = depositScanner.nextInt();
                    break;
                } catch (InputMismatchException var8) {
                    depositScanner.next();
                    System.out.println("숫자를 입력해 주세요.");
                }
            }

            list[idx].setIncome(list[idx].getIncome() + depositAmount);
            System.out.println("정상적으로 예금 되었습니다.");
        } else {
            System.out.println("존재하지 않는 계좌번호입니다.");
        }

    }

    public void withdraw(Account[] list) {
        System.out.println("----------");
        System.out.println("출금");
        System.out.println("----------");
        Scanner withdrawScanner = new Scanner(System.in);
        boolean withdrawFail = true;
        System.out.print("계좌번호: ");
        String depositAccount = withdrawScanner.next();

        int idx;
        for(idx = 0; idx < list.length; ++idx) {
            if (list[idx] != null && list[idx].getAccountNum().equals(depositAccount)) {
                withdrawFail = false;
                break;
            }
        }

        if (!withdrawFail) {
            System.out.print("출금액: ");

            int withdrawAmount;
            while(true) {
                try {
                    withdrawAmount = withdrawScanner.nextInt();
                    break;
                } catch (InputMismatchException var8) {
                    withdrawScanner.next();
                    System.out.println("숫자를 입력해 주세요.");
                }
            }

            if (list[idx].getIncome() - withdrawAmount < 0) {
                System.out.println("잔액 부족으로 출금이 불가능합니다.");
            } else {
                list[idx].setIncome(list[idx].getIncome() - withdrawAmount);
                System.out.println("정상적으로 출금 되었습니다.");
            }
        } else {
            System.out.println("존재하지 않는 계좌번호입니다.");
        }

    }
}
