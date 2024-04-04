package kr.co.kopo.classEx.inheritance_12_04w1;

public class AirplaneEx {
    public static void main(String[] args){
        SupersonicAirplane supersonicAirplane= new SupersonicAirplane("NORMAL");

        supersonicAirplane.departing();
        supersonicAirplane.flying();
        supersonicAirplane.setFlyMode("SUPERSONIC");
        supersonicAirplane.flying();
        supersonicAirplane.setFlyMode("NORMAL");
        supersonicAirplane.flying();
        supersonicAirplane.arriving();
    }
}
