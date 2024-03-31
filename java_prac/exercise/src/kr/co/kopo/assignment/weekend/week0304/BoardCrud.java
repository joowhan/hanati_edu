package kr.co.kopo.assignment.weekend.week0304;
import java.time.LocalDate;
import java.util.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BoardCrud {
    private static LinkedHashMap<Integer, UserText> boardList;
    BoardCrud() throws IOException {
        if(boardList == null){
            File file = new File("./list.csv");
            //이미 파일이 존재한다면 바로 데이터 읽기
            if(file.exists()){
                readDataCSV();
            }
            //파일이 존재하지 않는다면 새로 생성
            else{
                boardList = new LinkedHashMap<>();
                if(file.createNewFile()){
                    System.out.println("새롭게 파일을 생성합니다!");
                }
                else{
                    System.out.println("파일 생성 실패!");
                }
            }

        }

    }
    //비밀번호 검증
    public static boolean pwdValidation(String password){
        Pattern pwdPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{5,15}$");
        Matcher pwdMatcher = pwdPattern.matcher(password);
        if(!pwdMatcher.find()){
            return false;
        }
        return true;
    }

    public LinkedHashMap<Integer, UserText> getBoardList(){
        return boardList;
    }
    //CSV 데이터 읽기
    private void readDataCSV() throws IOException {
        BufferedReader br = null;
        try{
            br = Files.newBufferedReader(Paths.get("./list.csv"),Charset.forName("UTF-8"));
            System.out.println("데이터 읽기 성공!");
            String line="";
//            int idx = 0;
            boardList = new LinkedHashMap<>();
            while((line= br.readLine())!=null){
//                idx ++;
                String[] arr = line.split(",");

                String title = arr[0].trim();
                String writer = arr[1].trim();
                String date = arr[2].trim();
                int views = Integer.parseInt(arr[3]);
                boolean secret = Boolean.parseBoolean(arr[4]);
                String text = arr[5];
                String password = arr[6];
                // optional
                int idx =Integer.parseInt(arr[7]);
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
    //CSV파일 쓰기
    public boolean writeCSV(){
        File post = new File("list.csv");
        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter(post));
        for(Map.Entry<Integer,UserText> entry: boardList.entrySet()){
            String data="";
            UserText user = boardList.get(entry.getKey());
            String title = user.getTitle();
            String writer = user.getWriter();
            String date = user.getDate();
            int views = user.getViews();
            boolean secret = user.isSecret();
            String text = user.getText();
            String password = user.getPassword();
            int idx = user.getIdx();
            data = title +","+writer+","+date+","+views+","+secret+","+text+","+password+","+idx;
            bw.write(data);
            bw.newLine();
        }

        return true;
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(bw !=null){
                    bw.flush();
                    bw.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    //게시글 작성
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
            while(true){
                System.out.println("비밀번호는 영문자(대문자/소문자 1 이상 포함), 숫자 1이상 포함, 5~15자리여야 합니다.");
                System.out.print("비밀번호 입력: ");
                password = scanner.nextLine();
                if(pwdValidation(password)) break;
                else
                    System.out.println("비밀번호 형식이 올바르지 않습니다.");
            }


        }
        System.out.println();
        System.out.print("글 작성 > ");
        String text = scanner.nextLine();

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        date = now.format(formatter);

        idx = boardList.lastEntry().getKey()+1;
        boardList.put(idx, new UserText(idx, title,writer,date,views,secret,text,password));
        System.out.println("성공적으로 글이 저장되었습니다!");

    }
    //상세 조회
    private void readPost(UserText userText, int idx){
        Scanner scanner = new Scanner(System.in);

        int inputData;
        //비밀번호 일치 여부 확인
        if(!checkPassword(userText.isSecret(), userText.getPassword())){
            return;
        }
        //조회수 증가
        userText.setViews(userText.getViews()+1);

        System.out.println("----------------------------------------------------");
        System.out.println("제목: "+userText.getTitle());
        System.out.println("작성일자: "+ userText.getDate());
        System.out.println("게시글: "+ userText.getText());

        System.out.println("----------------------------------------------------");
        System.out.println("1. 글 수정 2. 글 삭제 3. 메인메뉴로 이동");
        System.out.print("선택 > ");
        inputData = scanner.nextInt();
        // Update
        if(inputData==1){
            if(updatePost(userText)){
                System.out.println("성공적으로 수정 되었습니다.");
            }
            else{
                System.out.println("수정 실패");
            }
        }

        //글 삭제
        else if(inputData==2){
            //비밀번호 입력
            deletePost(userText, idx);
        }
        //메인 메뉴로 이동
        else if(inputData==3){
            return;
        }
        else{
            System.out.println("잘못 입력하셨습니다.");
        }


    }
    //게시글 수정
    private boolean updatePost(UserText userText){
        Scanner scanner = new Scanner(System.in);
        String pwd;
        // 비밀번호 입력
        if(!checkPassword(userText.isSecret(), userText.getPassword())){
            return false;
        }

        System.out.print("수정할 제목을 입력하세요.: ");
        String title = scanner.nextLine();
        System.out.print("수정할 내용을 입력하세요. : ");
        String text = scanner.nextLine();
        System.out.println(text);
        userText.setTitle(title);
        userText.setText(text);

        return true;



    }
    //다중 삭제
    private void deletePost(String[] arr){

        for(int i=0;i<arr.length;i++){
            if(!boardList.keySet().contains(Integer.parseInt(arr[i]))){
                System.out.println("----------------------------------------------------");
                System.out.println(arr[i]+"는 존재하지 않는 번호입니다."+ boardList.keySet());
                System.out.println("----------------------------------------------------");
                return;
            }
            if(boardList.get(Integer.parseInt(arr[i])).isSecret()){
                System.out.println("----------------------------------------------------");
                System.out.println("비밀글이 포함되어 있습니다. 다중 삭제가 불가능합니다.");
                System.out.println("----------------------------------------------------");
                return;
            }

        }
        for(int i=0;i<arr.length;i++){
            boardList.remove(Integer.parseInt(arr[i]));
            System.out.println(arr[i]+"번 게시글을 삭제합니다.");
        }
        System.out.println("다중 삭제 완료!");
        System.out.println("----------------------------------------------------");
    }
    //상세 조회에서 삭제
    private void deletePost(UserText userText, int idx){
        if(!checkPassword(userText.isSecret(), userText.getPassword())){
            return;
        };
        boardList.remove(idx);
        System.out.println("성공적으로 삭제 되었습니다!");
        System.out.println("--------------------");
    }
    //목록 읽어오기
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
        System.out.println("===== 게시판 =====");
        System.out.println("----------------------------------------------------");
        while(true){
            for(Map.Entry<Integer,UserText> entry: boardList.entrySet()){
                System.out.println(boardList.get(entry.getKey()).toString());
            }
            System.out.println("----------------------------------------------------");
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
                if(boardList.isEmpty()){
                    System.out.println("글이 없습니다. 글을 새로 작성해주세요!");
                    continue;
                }
                System.out.print("조회할 번호를 입력하세요: ");
                docNum = scanner.nextInt();
                if(boardList.get(docNum) ==null){
                    System.out.println("해당 글은 존재하지 않습니다.");
                    System.out.println("----------------------------------------------------");
                    continue;
                }
                boardCrud.readPost(boardList.get(docNum), docNum);
            }
            else if(inputData==3){
                // 다중 삭제
                if(boardList.isEmpty()){
                    System.out.println("글이 없습니다. 글을 새로 작성해주세요!");
                    continue;
                }
                System.out.print("삭제할 글의 idx 번호를 입력해주세요. (띄어써서 입력): ");

                Scanner scanner1 = new Scanner(System.in);
                String temp = scanner1.nextLine();
                String[] arr = temp.split(" ");

                boardCrud.deletePost(arr);
            }
            else if(inputData==4){
                // 메인메뉴로 이동
                System.out.println("메인 메뉴로 이동합니다.");
                break;
            }
            else{
                System.out.println("다시 입력하세요.");
                System.out.println("----------------------------------------------------");
            }
        };
        return;
    }

    //비밀번호 일치 여부 확인
    private boolean checkPassword(boolean secret, String password) {
        System.out.println("비밀번호는 영문자(대문자/소문자 1 이상 포함), 숫자 1이상 포함, 5~15자리여야 합니다.");
        Scanner scanner = new Scanner(System.in);
        String pwd;
        if (secret == true) {
            System.out.print("비밀번호를 입력하세요: ");
            pwd = scanner.next();
            if (pwd.equals(password)) {
                System.out.println("비밀번호 일치!");
                return true;
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
                return false;
            }
        }
        return true;
    }

}
