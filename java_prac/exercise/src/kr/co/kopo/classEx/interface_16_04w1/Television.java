package kr.co.kopo.classEx.interface_16_04w1;

public class Television implements RemoteCotrol, Searchable, MachineControl{
    int volumn;
    Television(int volumn){
        this.volumn =  volumn;
    }
    @Override
    public void turnOn() {
        System.out.println("TV를 켭니다.");
        System.out.println("현재 volumn: "+this.volumn);
    }

    @Override
    public void turnOff() {
        System.out.println("TV를 끕니다.");
    }

    @Override
    public void search(String url) {
        System.out.println(url+" 검색합니다. ");
    }
}
