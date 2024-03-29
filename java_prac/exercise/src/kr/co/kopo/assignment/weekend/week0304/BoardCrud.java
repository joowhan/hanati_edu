package kr.co.kopo.assignment.weekend.week0304;
import java.time.LocalDate;
import java.util.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class BoardCrud {
    private static LinkedHashMap<Integer, UserText> boardList;
    BoardCrud() throws IOException {
        if(boardList == null){
            File file = new File("./list.csv");
            if(file.exists()){
                readDataCSV();
            }
            else{
                file.createNewFile();
            }

        }

    }
    public LinkedHashMap<Integer, UserText> getBoardList(){
        return boardList;
    }

    private void readDataCSV() throws IOException {
        BufferedReader br = null;
        try{
            br = Files.newBufferedReader(Paths.get("./list.csv"),Charset.forName("UTF-8"));
            System.out.println("데이터 읽기 성공!");
            String line="";
            int idx = 0;
            boardList = new LinkedHashMap<>();
            while((line= br.readLine())!=null){
                idx ++;
                String[] arr = line.split(",");

                String title = arr[0].trim();
                String writer = arr[1].trim();
                String date = arr[2].trim();
                int views = Integer.parseInt(arr[3]);
                boolean secret = Boolean.parseBoolean(arr[4]);
                String text = arr[5];
                String password = arr[6];

                boardList.put(idx, new UserText(idx, title,writer,date,views,secret,text,password));

            }
        }
        catch (NoSuchFileException e){
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writePost(){
        int idx = 0;
        int views = 0;
        String date;
        Scanner scanner = new Scanner(System.in);

        System.out.print("제목: ");
        String title = scanner.nextLine();

        System.out.print("작성자: ");
        String writer = scanner.nextLine();


        System.out.print("비밀글 여부: (Y/N)  ");
        String s = scanner.nextLine();
        String password;
        boolean secret;
        if(s.equals("N")){
            secret = false;
            password = "0";
        }
        else{
            secret = true;
            System.out.print("비밀번호 입력: ");
            password = scanner.nextLine();
        }
        System.out.println();
        System.out.print("글 작성 > ");
        String text = scanner.nextLine();

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        date = now.format(formatter);

        idx = boardList.size()+1;
        boardList.put(idx, new UserText(idx, title,writer,date,views,secret,text,password));





    }
    public void readPost(UserText userText){
        Scanner scanner = new Scanner(System.in);
        String pwd;
        if(userText.isSecret() == true){
            System.out.print("비밀번호를 입력하세요: ");
            pwd = scanner.next();
            if(pwd.equals(userText.getPassword())){
                System.out.println("비밀번호 일치!");
            }
            else{
                System.out.println("비밀번호가 일치하지 않습니다.");
                return;
            }
        }
        System.out.println("제목: "+userText.getTitle());
        System.out.println("작성일자: "+ userText.getDate());


    }
    public void deletePost(String[] arr){

    }
    public void deletePost(){

    }

    public static void readList(BoardCrud boardCrud){
        int inputData = 0;
        int docNum = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------------------------");
        //목록 출력
        LinkedHashMap<Integer, UserText> boardList = boardCrud.getBoardList();
        if(boardList.size()<1){
            System.out.println("작성된 글이 없습니다.");
        }
        for(int i=0;i<boardList.size();i++){
            System.out.println(boardList.entrySet());
        }
        System.out.println("----------------------------------------------------");
        while(true){
            System.out.println("===== 게시판 목록 =====");
            System.out.println("1. 글 작성 2. 상세 조회 3. 다중 삭제 4. 메인메뉴로 이동");
            System.out.print("선택 > ");
            inputData = scanner.nextInt();
            if(inputData==1){
                // 글 작성 -> 새롭게 글 생성
                boardCrud.writePost();
            }
            else if(inputData==2){
                // 상세 조회
                System.out.print("조회할 번호를 입력하세요: ");
                docNum = scanner.nextInt();
                boardCrud.readPost(boardList.get(docNum));
            }
            else if(inputData==3){
                // 다중 삭제
                String[] arr = scanner.nextLine().split(" ");
                boardCrud.deletePost(arr);
            }
            else if(inputData==4){
                // 메인메뉴로 이동
                System.out.println("메인 메뉴로 이동합니다.");
                break;
            }
            else{

            }
        };
        return;
    }

}
