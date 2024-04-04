package kr.co.javaex.ex18;

public class Str {
    public String str;

    public Str(String str) {
        this.str = str;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Str){
            Str st = (Str) obj;
            if (this.str.equals(st.str)){
                return true;
            }
        }
        return false;
    }
}
