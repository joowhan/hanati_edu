package kr.co.kopo.classEx.references_05_03w4;

import java.util.Scanner;

public class ClassEx_240325_3 {
    public static void main(String[] args) {
        int[] arr1;
        int[] arr2;
        int[] arr3;

        arr1 = new int[] {1,2,3};
        arr2 = new int[] {1,2,3};
        arr3 = arr2;

        System.out.println(arr1==arr2);
        System.out.println(arr2==arr3);


        String name1 = "홍길동";
        String name2 = "홍길동";
        String name3 = new String("홍길동"); //new로 생성하면 다른 객체다.

        System.out.println(name1==name2);
        System.out.println(name1==name3);

        String[] strList = new String[]{"hello", "world"};
        System.out.println("length: "+ strList.length);

        printItem(new String[]{"hello", "world"});

        int[] score = new int[]{83,90,87,85,95};
        int a = 0;
        int total = 0;
        int avg = 0;

        while(a < score.length){
            total += score[a];
            a++;
        }
        avg= total/score.length;
        System.out.println(total +" "+avg);

        //
        int[][] arr = {
                {1,2},
                {1,2,3}
        };
        System.out.println(arr[1][2]);

        //
        int[][] scores = new int[2][3];
        int c = 1;
        for(int i=0;i<scores.length;i++){
            for(int j=0;j<scores[0].length;j++){
                scores[i][j] = 10*c;
                c++;
            }
        }

        //향상된 for문
        int q=0;
        int e=0;
        for(int[] w: scores){
            e=0;
            for(int k:w){
                System.out.println(q+" "+e+" "+k);
                e++;
            }
            q++;

        }

        for(int i=0;i<scores.length;i++){
            for(int j=0;j<scores[0].length;j++){
                System.out.println(scores[i][j]);
            }
        }
        //-----------------------------------------------
        //2.
        int[] points = new int[4];
        double[] weights = new double[5];
        boolean[] answers = new boolean[3];
        String[] names = new String[3];
        //3.
        int[] array = {1,5,3,8,2};
        System.out.println(maxVal(array));
        //4.
        int sum = 0;
        int average = 0;
        int l = 0;
        int[][] array1 ={
                {95, 86},
                {83,92,96},
                {78,83,93,87,88}
        };
        for(int i=0;i<array1.length;i++){
            for(int j=0;j<array1[i].length;j++){
                sum += array1[i][j];
                l++;
            }
        }
        average = sum/l;
        System.out.println(sum + " "+ average );
        //5.
        int[] moneyList = {1593200,57800,193500};
        for(int i=0;i<moneyList.length;i++)
            System.out.println(moneyList[i]);
        for(int money: moneyList){
            System.out.println(money);
        }
        //6.
        int[] count = null;
        double[] heights = {191.3, 185.0};
//        System.out.println(count[0]); //NullPointerException
//        System.out.println(heights[2]); //.ArrayIndexOutOfBoundsException
        //7.

        int student = 0;
        String[] scoreList =null;
        int maxValue = 0;
        int input = 0;
        int v = 0;
        int scoreSum = 0;
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("1.학생수 | 2.점수입력 | 3.점수리스트 | 4.분석 | 5.종료");
            input  = scanner.nextInt();

            if(input ==1){

                System.out.println("1.학생수를 입력하세요");
                scanner = new Scanner(System.in);
                student = scanner.nextInt();
                if(student <10){
                    System.out.println("10명 이상 입력하세요.");
                    student = 0;
                }

            }
            else if(input==2){
                scoreList = new String[student];
                scanner = new Scanner(System.in);
                String temp = scanner.nextLine();
                scoreList = temp.split(" ");
            }
            else if(input==3){

                for(int i=0;i<scoreList.length;i++){
                    System.out.print(scoreList[i]+"\t");
                }
                System.out.println();
            }
            else if(input==4){
                // isdigit 검사

                for(int i=0;i<scoreList.length;i++){
                    v = Integer.parseInt(scoreList[i]);
                    if(v > maxValue)
                        maxValue = v;
                    scoreSum += v;
                }

                System.out.println("평균은 "+(scoreSum/scoreList.length)+", 최고 점수는 "+ maxValue );
            }
            else{
                break;
            }
        }


    }
    public static int maxVal(int[] numList){
        int max = 0;
        for(int i=0;i<numList.length;i++){
           if(numList[i] > max){
               max = numList[i];
           }
        }
        return max;
    }
    public static void printItem(String[] strList){
        System.out.println("length: "+ strList.length);
    }
}
