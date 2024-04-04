package kr.co.javaex.ex19;

public class PersonCustomException extends Exception {
    PersonCustomException(){

    }
    PersonCustomException(String message){
        super(message);
    }
    int age;
    public void setAge(int age) throws PersonCustomException {
        if (age < 0) {
            throw new MyException("나이 : "+age+", 오류 메세지: 나이는 음수가 될 수 없습니다.");
        }
    }


}
