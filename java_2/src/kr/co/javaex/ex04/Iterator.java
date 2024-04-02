package kr.co.javaex.ex04;

public class Iterator {
    public Iterator() {
    }

    public static void main(String[] args) {
        int sum = 0;

        int i;
        for(i = 1; i <= 100; ++i) {
            sum += i;
        }

        System.out.println("1~100 합: " + sum);

        int s;
        for(i = 1; i <= 3; ++i) {
            System.out.println("i: " + i);

            for(s = 1; s <= 10; ++s) {
                System.out.println("" + s + " + " + i + " = " + (s + i));
            }

            System.out.println();
        }

        i = 0;

        for(sum = 0; i < 10; sum += i) {
            ++i;
        }

        System.out.println("sum: " + sum);
        i = 0;
        sum = 0;

        do {
            ++i;
            sum += i;
        } while(i < 10);

        System.out.println("sum :" + sum);

        for(s = 2; s < 6; ++s) {
            for(int t = 1; t < 10; ++t) {
                System.out.print("" + s + " * " + t + " = " + s * t);
                System.out.print("\t");
            }

            System.out.println();
        }

        System.out.println();

        do {
            s = (int)(Math.random() * 10.0) + 1;
            System.out.println(s);
        } while(s != 9);

        System.out.println();

        do {
            s = (int)(Math.random() * 10.0) + 1;
            System.out.println(s);
        } while(s != 9);

        System.out.println();

        label73:
        for(char upper = 'A'; upper <= 'Z'; ++upper) {
            for(char lower = 'a'; lower <= 'z'; ++lower) {
                System.out.print(upper);
                System.out.println("" + upper + "-" + lower);
                if (lower == 'h') {
                    break label73;
                }
            }
        }

        System.out.println();

        for(s = 0; s <= 10; ++s) {
            if (s % 2 == 0) {
                System.out.println("" + s + " 짝수");
            }
        }

        System.out.println();

        for(s = 0; s < 11; ++s) {
            if (s % 2 != 0) {
                System.out.println("" + s + " 홀수");
            }
        }

    }
}
