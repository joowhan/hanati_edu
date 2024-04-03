package kr.co.kopo.assignment.mid.Java_15_1_ex1_starcaroptionexam;

public class StarCarEx {
    public static void main(String[] args) {
        StarCarHighGrade starCarHighGrade = new StarCarHighGrade("레드", "광폭타이어", 2200, "파워핸들");
        StarCarLowGrade starCarLowGrade = new StarCarLowGrade("블루", "일반타이어", 2000, "파워핸들");
        //저사양 옵션 및 세금 조회
        starCarLowGrade.getSpec();
        System.out.println();
        //고사양 옵션 및 세금 조회
        starCarHighGrade.getSpec();

    }
}
