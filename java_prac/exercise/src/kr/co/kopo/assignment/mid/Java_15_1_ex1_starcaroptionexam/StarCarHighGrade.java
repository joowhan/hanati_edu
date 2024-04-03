package kr.co.kopo.assignment.mid.Java_15_1_ex1_starcaroptionexam;

public class StarCarHighGrade extends StarCar{
    private int tax=0;
    StarCarHighGrade(String color, String tire, int displacement, String handle){
        super();
        this.color = color;
        this.tire = tire;
        this.displacement = displacement;
        this.handle = handle;
    }


    @Override
    public int getSpec() {
        //배기량이 기준값 보다 더 적다면
        if(CarSpecs.DISPLACEMENT_STD > displacement){
            tax = 1000;
        }
        else{
            tax = 2000;
        }

        System.out.println("[StarCar 고사양 옵션 및 세금]");
        System.out.println("---------------------------");
        System.out.println(this);
        return 0;
    }

    @Override
    public String toString() {
        return
                "색상: " + color + "\n"+
                        "타이어: " + tire + "\n"+
                        "배기량: " + displacement + "\n"+
                        "핸들: " + handle + "\n"+
                        "세금: " + tax;
    }
}
