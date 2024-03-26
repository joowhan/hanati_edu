package kr.co.kopo.classEx.operator_03_03w4;
import java.util.*;
import java.io.*;

public class ClassEx_240325_2 {
    public static void main(String[] args) throws IOException {
        //1.
        int x = 5;
        int y = 10;
        String answer = "\"x+y는 " +(x+y)+ "\"";
        System.out.println(answer);

        //3.
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        if(a%3==0){
            System.out.println("3의 배수입니다.");
        }
        else{
            System.out.println("3의 배수가 아닙니다.");
        }

        //4.

        System.out.println("화면에 점을 보세요");
        System.out.println("이름을 입력해 주세요");
        Scanner scanner1 = new Scanner(System.in);
        String name = scanner1.nextLine();
        System.out.println("나이를 입력해 주세요");
        Scanner scanner2 = new Scanner(System.in);
        String ageString = scanner2.nextLine();
        int age = Integer.parseInt(ageString);
        int fortune = new Random().nextInt(3);
        fortune ++;
        System.out.println("점꾀가 나왔습니다.");
        System.out.println(age+"살의 "+name+"씨, 당신의 운세번호는 "+fortune+ " 입니다.");

//        Map<String, String> ht = new HashMap<String, String>();
        System.out.println("1:대박 2:중박 3:보통 4:망" );

        // StringTokenizer
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());

//        System.out.println("화면에 점을 보세요");
//        System.out.println("이름을 입력해 주세요");
//        String name = scanner.nextLine();
//        System.out.println("나이를 입력해 주세요");
//        String ageString = scanner.nextLine();
//        int age = Integer.parseInt(ageString);
//        int fortune = new Random().nextInt(3);
//        fortune ++;
//        System.out.println("점꾀가 나왔습니다.");
//        System.out.println(age+"살의 "+name+"씨, 당신의 운세번호는 "+fortune+ " 입니다.");
//        System.out.println("1:대박 2:중박 3:보통 4:망" );

    }
}
