package kr.co.javaex.ex19;

public class PersonStandardException {
    int age;

    public void setAge(int age) {
        try {
            if (age < 0) {
                throw new IllegalArgumentException("나이: " + age + ", [오류 메세지: 나이는 음수가 될 수 없습니다.]");
            }
            this.age = age;
        } catch (IllegalArgumentException e) {
            System.out.println("오류 발생: " + e.getMessage());
        }
    }
}
