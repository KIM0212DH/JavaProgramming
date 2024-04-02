package kr.co.javaex.ex08;

public class Korean {
    final String name = "대한민국";
    final String ssn;

    public String getName() {
        return "대한민국";
    }

    public String getSsn() {
        return this.ssn;
    }

    public Korean(String ssn) {
        this.ssn = ssn;
    }
}
