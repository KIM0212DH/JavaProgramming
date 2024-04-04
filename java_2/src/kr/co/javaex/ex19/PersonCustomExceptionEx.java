package kr.co.javaex.ex19;

public class PersonCustomExceptionEx {
    public static void main(String[] args) {
        PersonCustomException personCustomException = new PersonCustomException();

        try {
            personCustomException.setAge(-30);
        } catch (PersonCustomException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
