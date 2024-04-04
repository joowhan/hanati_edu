package kr.co.kopo.classEx.api_18_04w1;

public class StringEx {
    public static void main(String[] args) {
        String a = "java";
        String b = " JAVA ";
        if(a.equals(b.toLowerCase().trim())){
            System.out.println("똑같다.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("집에");
        sb.insert(2, " 안가고 싶다");
        System.out.println(sb);
        sb.delete(3,4);
        System.out.println(sb);
        sb.replace(7,8,"을까?");
        System.out.println(sb);
    }
}
