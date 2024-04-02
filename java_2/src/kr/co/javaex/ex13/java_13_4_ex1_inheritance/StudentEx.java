package kr.co.javaex.ex13.java_13_4_ex1_inheritance;

public class StudentEx {
    public static void personInfo(Person person) {
        System.out.println("name: " + person.name);
        person.walk();

        if (person instanceof Student) {
            Student student = (Student) person;
            System.out.println("studentNo: " + student.studentNo);
            student.study();
        }
        if (person instanceof Student student) {
            System.out.println("studentNO: " + student.studentNo);
            student.study();
        }
    }

    public static void main(String[] args) {
        Person person1 = new Person("홍길이");
        personInfo(person1);

        System.out.println();

        Person person2 = new Student("홍순이", 30);
        personInfo(person2);
    }
}
