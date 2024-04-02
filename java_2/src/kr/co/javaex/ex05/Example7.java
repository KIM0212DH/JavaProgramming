package kr.co.javaex.ex05;

import java.util.Scanner;

public class Example7 {
    public Example7() {
    }

    public static void main(String[] args) {
        boolean keep = true;
        int stuCnt = 0;
        int[] students = null;
        Scanner menuScanner = new Scanner(System.in);
        Scanner intScanner = new Scanner(System.in);

        while(true) {
            while(keep) {
                System.out.println("---------------------------------------------------");
                System.out.println("1.학생 수 | 2.점수 입력 | 3.점수 리스트 | 4.분석 | 5.종료");
                System.out.println("---------------------------------------------------");
                System.out.print("선택>");
                int menu = Integer.parseInt(menuScanner.nextLine().trim());
                System.out.println(menu);
                if (menu == 1) {
                    System.out.print("학생 수를 입력하세요.>");
                    stuCnt = intScanner.nextInt();
                    if (stuCnt < 10) {
                        System.out.println("학생 수는 10명 이상이어야 합니다.");
                    } else {
                        students = new int[stuCnt];
                        System.out.println("학생 수는 " + stuCnt + "명 입니다.");
                    }
                } else {
                    int maxScore;
                    if (menu == 2) {
                        if (students == null) {
                            System.out.println("학생 수를 먼저 입력하세요.");
                        } else {
                            for(maxScore = 0; maxScore < stuCnt; ++maxScore) {
                                System.out.print("학생의 점수를 하나씩 입력하세요. " + (stuCnt - maxScore) + "명 남았습니다.>");
                                students[maxScore] = intScanner.nextInt();
                            }
                        }
                    } else {
                        int sum;
                        if (menu == 3) {
                            if (students == null) {
                                System.out.println("학생 수와 점수를 먼저 입력하세요.");
                            } else {
                                System.out.println("점수 리스트를 출력합니다.");
                                int[] var15 = students;
                                sum = students.length;

                                for(int var16 = 0; var16 < sum; ++var16) {
                                    int score = var15[var16];
                                    System.out.print("" + score + " ");
                                }

                                System.out.println();
                            }
                        } else if (menu != 4) {
                            if (menu == 5) {
                                keep = false;
                                System.out.println("종료합니다.");
                            }
                        } else if (students == null) {
                            System.out.println("학생 수와 점수를 먼저 입력하세요");
                        } else {
                            maxScore = 0;
                            sum = 0;
                            int[] var11 = students;
                            int var12 = students.length;

                            for(int var13 = 0; var13 < var12; ++var13) {
                                int score = var11[var13];
                                if (score > maxScore) {
                                    maxScore = score;
                                }

                                sum += score;
                            }

                            double avg = (double)sum / (double)stuCnt;
                            System.out.println("최고 점수 : " + maxScore + ", 평균 점수 : " + avg);
                        }
                    }
                }
            }

            menuScanner.close();
            intScanner.close();
            return;
        }
    }
}
