package kr.co.kopo.classEx.class_08_03w4;

public class ClassEx_240328_2 {
    public static void main(String[] args) {
        //예제1,2
        Member user1 = new Member("홍길동","hong");
        //예제3
        if(user1.login("hong","12345")){
            System.out.println("로그인 성공!");
        }
        user1.logout(user1.getId());
        //예제 4
        Printer printer = new Printer();
        printer.println(10);
        printer.println(true);
        printer.println(5.7);
        printer.println("홍길동");
    }

}
