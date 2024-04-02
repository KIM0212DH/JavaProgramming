package kr.co.javaex.ex08;

public class MemberService {
    public MemberService() {
    }

    public boolean login(String id, String password) {
        return id.equals("hong") && password.equals("12345");
    }

    public void logout(String id) {
        System.out.println(id + "님이 로그아웃 되었습니다.");
    }
}
