package kr.co.kopo.classEx.api_18_04w1;

import java.io.IOException;

public class KeyInExam {
    public static void main(String[] args) throws IOException {
        int keyCode;
        int speed = 0;
        while(true){
            System.out.println(speed);
            keyCode = System.in.read();
            if(keyCode==49){
                speed++;
            }
            else if(keyCode==50){
                speed--;
            }
            else if(keyCode==51){
                System.exit(0);
            }

        }
    }
}
