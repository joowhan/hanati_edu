package kr.co.kopo.classEx.loop_03w4;

import java.io.*;
import java.util.*;

public class ClassEx_240325_3 {
    public static void main(String[] args) throws IOException{
        //if-else
        int score = 91;
        if(score >= 90){
            System.out.println("점수가 90보다 큽니다.");
        }
        else if(score >= 80)
            System.out.println("점수가 낮아요...");
        else{
            System.out.println("점수가 90보다 작습니다.");
        }

        //switch
        int num;
        num = (int)(Math.random()*5)+1;
        switch(num){
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;
            case 4:
                System.out.println("4");
                break;
            default:
                System.out.println("5");
                break;
        }
        for(int i=2;i<=5;i++){
            System.out.println("i: "+i);
            for(int j=1;j <=9;j++){
                System.out.print(i+"*"+j+ "="+ (j*i)+"\t");
            }
            System.out.println();
        }

        // while
        int i = 0;
        int sum = 0;
        while(i<100){
            i++;
            sum += i;
        };
        System.out.println("sum: "+sum);

        //do-while
        i=0;
        sum=0;
        do{
            i++;
            sum +=i;
        }while(i<10);
        System.out.println("sum; "+sum);

        // break
        while(true){
            int k = (int) (Math.random()*10)+1;
            System.out.println(k);
            if(k==5)
                break;
        }

        Outer: for(char upper ='A';upper<='Z';upper ++){
            for(char lower='a';lower<='z';lower++){
                System.out.println(upper +'-'+ lower); //'-'로 하면 가장 범위가 가장 큰 타입으로 결과가 나온다.
                System.out.println((int)upper +" "+ (int)lower+" "+(int)'-' );
                if(lower=='h')
                    break Outer;
            }
        }

        for(int j=0;j<=10;j++){
            if(j%2==0)
                continue;
            System.out.println(j+"홀수");
        }
        int z = 0;
        while(z<10){
            z++;
            if(z%2==0)
                continue;
            System.out.println(z+"홀수");
        }


        //------------예제--------------
        //1.
        Scanner scanner = new Scanner(System.in);
        int math = scanner.nextInt();
        scanner = new Scanner(System.in);
        int korean = scanner.nextInt();
        scanner = new Scanner(System.in);
        int english = scanner.nextInt();

        double avg = (math+korean+english)/3;

        if(math>avg)
            System.out.println("수학은 평균 이상입니다.");
        if(korean>avg)
            System.out.println("국어는 평균 이상입니다.");
        if(english>avg)
            System.out.println("영어는 평균 이상입니다.");

        //2.
        int total = 0;
        int even=0;
        int odd=0;
        for(int k=1;k<=100;k++){
            if(k%2==0)
                even +=k;
            else
                odd +=k;
        }
        System.out.println(even+" "+odd);

        //3.
        for(int j=1;j<=9;j++){
            for(int k=1;k<=9;k++){
                System.out.print(j+" * "+k+" = "+(j*k)+"\t");
            }
            System.out.println();
        }
        //4.
        while(true){
            int a = (int)(Math.random()*6)+1;
            int b = (int)(Math.random()*6)+1;;
            if(a+b==5){
                System.out.println(a+" "+b);
                break;
            }

        }
        //5.
        for(int x=1;x<=10;x++){
            for(int y=1;y<=10;y++){
                if(4*x+5*y==60)
                    System.out.println("("+x+","+y+")");
            }
        }
        //6. 
        scanner = new Scanner(System.in);
        int menuNo = scanner.nextInt();
        switch (menuNo){
            case 1:
                System.out.println("검색합니덩");
                break;
            case 2:
                System.out.println("등록합니덩");
                break;
            case 3:
                System.out.println("삭제합니덩");
                break;
            case 4:
                System.out.println("변경합니덩");
                break;
        }

    }
}
