package kr.co.kopo.classEx.api_18_04w1;

import kr.co.kopo.classEx.inheritance_12_04w1.SupersonicAirplane;

import java.io.IOException;

public class SystemEx {
    public static void main(String[] args) throws IOException {
        int keyCode = 0;
        while(true){
            if(keyCode!=13 && keyCode!=10){
                if(keyCode == 49){
                    System.out.println(keyCode);
                    System.exit(0);
                }
            }
            keyCode = System.in.read();
        }
    }
}
