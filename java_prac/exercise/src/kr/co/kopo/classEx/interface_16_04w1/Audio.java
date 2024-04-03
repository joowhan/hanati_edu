package kr.co.kopo.classEx.interface_16_04w1;

public class Audio implements RemoteCotrol{
    int volumn;
    Audio(int volumn){
        this.volumn = volumn;
    }
    @Override
    public void turnOn() {
        System.out.println("Audio를 켭니다.");
        System.out.println("현재 volumn: "+this.volumn);
    }

    @Override
    public void turnOff() {
        System.out.println("Audio를 끕니다.");
    }
}
