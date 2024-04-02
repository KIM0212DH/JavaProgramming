package kr.co.javaex.ex09.java_09_3_ex1_userManager;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MemberEx {
    public MemberEx() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Member> list = new ArrayList(100);
        MemberEx memberEx = new MemberEx();
        boolean keep = true;

        while(keep) {
            System.out.println("\n===== 회원관리 및 로그인 프로그램 =====");
            System.out.println("1. 회원 추가");
            System.out.println("2. 회원 삭제");
            System.out.println("3. 회원 조회");
            System.out.println("4. 로그인");
            System.out.println("5. 로그아웃");
            System.out.println("6. 종료");
            System.out.print("메뉴를 선택하세요:");

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
                    memberEx.createMember(list);
                    break;
                case 2:
                    memberEx.deleteMember(list);
                    break;
                case 3:
                    memberEx.showList(list);
                    break;
                case 4:
                    memberEx.logIn(list);
                    break;
                case 5:
                    memberEx.logOut(list);
                    break;
                case 6:
                    keep = false;
                    System.out.println("프로그램 종료");
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요");
            }
        }

    }

    public void createMember(List<Member> list) {
        boolean createFail = false;
        Scanner createScanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------");
        System.out.print("새로운 id 입력: ");
        String id = createScanner.next();
        if (this.checkValidId(id)) {
            Iterator var5 = list.iterator();

            while(var5.hasNext()) {
                Member member = (Member)var5.next();
                if (member.getId().equals(id)) {
                    createFail = true;
                    System.out.println("중복된 id 불가");
                    break;
                }
            }

            if (!createFail) {
                System.out.print("비밀번호: ");
                String password = createScanner.next();
                if (this.checkValidPassword(password)) {
                    System.out.print("이름: ");
                    String name = createScanner.next();
                    System.out.print("이메일: ");
                    String email = createScanner.next();
                    System.out.print("전화번호: ");
                    String phoneNum = createScanner.next();
                    Member newMember = new Member();
                    newMember.setId(id);
                    newMember.setPassword(password);
                    newMember.setName(name);
                    newMember.setEmail(email);
                    newMember.setPhoneNum(phoneNum);
                    list.add(newMember);
                } else {
                    createFail = true;
                }
            }
        } else {
            createFail = true;
        }

        if (!createFail) {
            System.out.println("회원을 추가하였습니다.");
        } else {
            System.out.println("회원 추가에 실패하였습니다.");
        }

    }

    public boolean checkValidId(String id) {
        if (id.length() >= 5 && id.length() <= 15) {
            for(int i = 0; i < id.length(); ++i) {
                char c = id.charAt(i);
                if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9')) {
                    System.out.println("허용된 숫자나 영어가 아닙니다.");
                    return false;
                }
            }

            return true;
        } else {
            System.out.println("허용된 길이의 id가 아닙니다.");
            return false;
        }
    }

    public boolean checkValidPassword(String password) {
        if (password.length() >= 5 && password.length() <= 15) {
            int upperCnt = 0;
            int lowerCnt = 0;
            int numCnt = 0;

            for(int i = 0; i < password.length(); ++i) {
                char c = password.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    ++lowerCnt;
                }

                if (c >= 'A' && c <= 'Z') {
                    ++upperCnt;
                }

                if (c >= '0' && c <= '9') {
                    ++numCnt;
                }
            }

            if (upperCnt != 0 && lowerCnt != 0 && numCnt != 0) {
                return true;
            } else {
                System.out.println("영어 대소문자, 숫자 입력이 필요합니다.");
                return false;
            }
        } else {
            System.out.println("허용된 길이의 password가 아닙니다.");
            return false;
        }
    }

    public void deleteMember(List<Member> list) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("회원 삭제");
        boolean deleteComplete = false;
        Scanner deleteScanner = new Scanner(System.in);
        System.out.print("삭제할 회원 id: ");
        String id = deleteScanner.next();
        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            Member member = (Member)var5.next();
            if (member.getId().equals(id)) {
                deleteComplete = true;
                list.remove(member);
                break;
            }
        }

        if (deleteComplete) {
            System.out.println("회원을 삭제하였습니다.");
        } else {
            System.out.println("해당 id의 회원이 존재하지 않습니다.");
        }

    }

    public void showList(List<Member> list) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("회원 목록");
        System.out.println("-----------------------------------------------------------------");
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            Member show = (Member)var2.next();
            System.out.println(show.toString());
        }

        System.out.println("-----------------------------------------------------------------");
    }

    public void logIn(List<Member> list) {
        Scanner logInScanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------");
        System.out.print("로그인 할 ID 입력: ");
        String tryId = logInScanner.next();
        boolean loginComplete = false;
        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            Member member = (Member)var5.next();
            if (tryId.equals(member.getId())) {
                if (member.isStatus()) {
                    System.out.println("이미 로그인 되어 있습니다.");
                    return;
                }

                loginComplete = true;
                member.setStatus(true);
                break;
            }
        }

        if (loginComplete) {
            System.out.println(tryId + " 로그인 성공");
        } else {
            System.out.println(tryId + " 로그인 실패");
        }

    }

    public void logOut(List<Member> list) {
        Scanner logOutScanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------");
        System.out.print("로그아웃 할 ID 입력: ");
        String tryId = logOutScanner.next();
        boolean logOutComplete = false;
        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            Member member = (Member)var5.next();
            if (tryId.equals(member.getId())) {
                if (!member.isStatus()) {
                    System.out.println("로그인 되어 있는 계정이 아닙니다.");
                    return;
                }

                logOutComplete = true;
                member.setStatus(false);
                break;
            }
        }

        if (logOutComplete) {
            System.out.println(tryId + " 로그아웃 성공");
        } else {
            System.out.println(tryId + " 로그아웃 실패");
        }

    }
}
