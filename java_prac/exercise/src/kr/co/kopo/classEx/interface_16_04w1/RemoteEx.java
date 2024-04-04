package kr.co.kopo.classEx.interface_16_04w1;

public class RemoteEx {
    public static void main(String[] args) {
        Television rc = new Television(5);
        RemoteCotrol rc2 = new Audio(5);
        MachineControl rc3 = new Television(4);
        rc.turnOn();
        rc.turnOff();

        rc2.turnOn();
        rc2.turnOff();
    }
}
