package kr.co.kopo.classEx.inheritance_12_04w1;

public final class SupersonicAirplane extends Airplane{

    private String flyMode ="NORMAL";

    public String getFlyMode() {
        return flyMode;
    }

    public void setFlyMode(String flyMode) {
        this.flyMode = flyMode;
    }

    SupersonicAirplane(String flyMode){
        this.flyMode = flyMode;
    }
    public void flying(){
        if(flyMode.equals("NORMAL")){
            super.flying();
        }
        else{
            System.out.println("초음속 비행합니다.");
        }
    }
}
