package kr.co.javaex.ex09.java_09_1_ex1_studentManager;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    public StudentManager() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> list = new ArrayList(100);
        StudentManager studentManager = new StudentManager();
        boolean keep = true;

        while(keep) {
            System.out.println("-------------------------------------------------------");
            System.out.println("1.학생 추가 | 2.학생 목록 | 3.학생 수정 | 4.학생 삭제 | 5.종료");
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
                    studentManager.createStudent(list);
                    break;
                case 2:
                    studentManager.showList(list);
                    break;
                case 3:
                    studentManager.editStudent(list);
                    break;
                case 4:
                    studentManager.deleteStudent(list);
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

    public void createStudent(List<Student> list) {
        System.out.println("----------");
        System.out.println("학생 추가");
        System.out.println("----------");
        Scanner createScanner = new Scanner(System.in);
        boolean createFail = false;

        int key;
        while(true) {
            try {
                System.out.print("학번(Key): ");
                key = createScanner.nextInt();
                break;
            } catch (InputMismatchException var10) {
                createScanner.next();
                System.out.println("숫자를 입력해 주세요.");
            }
        }

        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            Student student = (Student)var5.next();
            if (student.getKey() == key) {
                createFail = true;
                break;
            }
        }

        if (!createFail) {
            System.out.print("이름: ");
            String name = createScanner.next();

            int age;
            while(true) {
                try {
                    System.out.print("나이: ");
                    age = createScanner.nextInt();
                    break;
                } catch (InputMismatchException var9) {
                    createScanner.next();
                    System.out.println("숫자를 입력해 주세요.");
                }
            }

            System.out.print("전공: ");
            String major = createScanner.next();
            Student newStudent = new Student();
            newStudent.setKey(key);
            newStudent.setName(name);
            newStudent.setAge(age);
            newStudent.setMajor(major);
            list.add(newStudent);
            System.out.println("학생을 추가하였습니다.");
        } else {
            System.out.println("이미 존재하는 학번(key)입니다.");
        }

    }

    public void showList(List<Student> list) {
        System.out.println("----------");
        System.out.println("학생 목록");
        System.out.println("----------");
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            Student show = (Student)var2.next();
            PrintStream var10000 = System.out;
            String var10001 = show.getName();
            var10000.println(var10001 + "\t" + show.getAge() + "\t" + show.getKey() + "\t" + show.getMajor());
        }

        System.out.println("-------------------------------------------------------");
    }

    public void editStudent(List<Student> list) {
        System.out.println("----------");
        System.out.println("학생 수정");
        System.out.println("----------");
        boolean editComplete = false;
        Scanner editScanner = new Scanner(System.in);

        int key;
        while(true) {
            try {
                System.out.print("학번(Key): ");
                key = editScanner.nextInt();
                break;
            } catch (InputMismatchException var11) {
                editScanner.next();
                System.out.println("숫자를 입력해 주세요.");
            }
        }

        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            Student student = (Student)var5.next();
            if (student.getKey() == key) {
                editComplete = true;
                System.out.print("이름: ");
                String name = editScanner.next();

                int age;
                while(true) {
                    try {
                        System.out.print("나이: ");
                        age = editScanner.nextInt();
                        break;
                    } catch (InputMismatchException var10) {
                        editScanner.next();
                        System.out.println("숫자를 입력해 주세요.");
                    }
                }

                System.out.print("전공: ");
                String major = editScanner.next();
                student.setName(name);
                student.setAge(age);
                student.setMajor(major);
                break;
            }
        }

        if (editComplete) {
            System.out.println("학생을 수정하였습니다.");
        } else {
            System.out.println("해당 학번(key)의 학생이 존재하지 않습니다.");
        }

    }

    public void deleteStudent(List<Student> list) {
        System.out.println("----------");
        System.out.println("학생 삭제");
        System.out.println("----------");
        boolean deleteComplete = false;
        Scanner deleteScanner = new Scanner(System.in);

        int key;
        while(true) {
            try {
                System.out.print("학번(Key): ");
                key = deleteScanner.nextInt();
                break;
            } catch (InputMismatchException var7) {
                deleteScanner.next();
                System.out.println("숫자를 입력해 주세요.");
            }
        }

        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            Student student = (Student)var5.next();
            if (student.getKey() == key) {
                deleteComplete = true;
                list.remove(student);
                break;
            }
        }

        if (deleteComplete) {
            System.out.println("학생을 삭제하였습니다.");
        } else {
            System.out.println("해당 학번(key)의 학생이 존재하지 않습니다.");
        }

    }
}
