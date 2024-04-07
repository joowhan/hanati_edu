package kr.co.kopo.assignment.weekend.week0401;


import oracle.jdbc.proxy.annotation.Pre;

import javax.xml.transform.Result;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BoardCrud implements Crud{
    Connection connection =null;
    Scanner scanner = null;
    //객체 생성
    BoardCrud(){
        try{
            this.connection = DbConnection.dbConnected();
            scanner = new Scanner(System.in);
        } catch (SQLException e) {
            System.out.println("DB 연결에 실패하였습니다.");
            throw new RuntimeException(e);
        }
    }

    // 테이블에 저장된 게시글 정보 불러오기
    @Override
    public void readTable() {
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        Scanner scanner = new Scanner(System.in);
        int inputData;
        int docNum;
        List<Integer> nb_boardList = new ArrayList<>();
        try{
            while(true){
//                String sql = new StringBuilder().append("SELECT *").append("FROM TB_BOARD").toString();
                String sql ="SELECT B.NB_BOARD, B.NM_TITLE, B.NM_CONTENT, B.NM_WRITER, B.DA_WRITER, B.CN_HIT, C.NM_ORG_FILE " +
                        "FROM TB_BOARD B LEFT JOIN TB_CONTENT C ON B.ID_FILE = C.ID_FILE " +
                        "ORDER BY B.NB_BOARD DESC";
                pstmt = connection.prepareStatement(sql);
                rs = pstmt.executeQuery();
                System.out.println("-------------------------------------------------");
                System.out.println("===== 게시판 목록 =====");
                while(rs.next()){

                    System.out.println("글 번호 : "+ rs.getInt(1) +" | "+ "제목: "+ rs.getString(2)
                            +" | "+ "작성자: "+ rs.getString(4) +" | "+ "작성일: "+ rs.getString(5)
                            +" | "+ "조회수: "+ rs.getInt(6) +" | "+ "파일 존재: "+ rs.getString(7));
                    nb_boardList.add(rs.getInt(1));
                }
                System.out.println("-------------------------------------------------");
                System.out.println("1. 글 작성 2. 상세 조회 3. 다중 삭제 4. 메인메뉴로 이동");
                System.out.print("선택 > ");
                inputData = scanner.nextInt();

                if(inputData==1){
                    // 글 작성 -> 새롭게 글 생성
                    if(this.insertPost()){
                        System.out.println("글을 성공적으로 저장했습니다.");
                    }
                    else{
                        System.out.println("글 저장에 실패했습니다.");
                    }

                }
                else if(inputData==2){
                    // 상세 조회

                    if(nb_boardList.size() ==0){
                        System.out.println("글이 없습니다. 글을 새로 작성해주세요!");
                        continue;
                    }
                    System.out.print("조회할 번호를 입력하세요: ");
                    docNum = scanner.nextInt();
                    if(!nb_boardList.contains(docNum)){
                        System.out.println("해당 글은 존재하지 않습니다.");
                        System.out.println("----------------------------------------------------");
                        continue;
                    }
                    this.readPost(docNum);
                }
                else if(inputData==3){
                    // 다중 삭제
                    if(nb_boardList.size() ==0){
                        System.out.println("글이 없습니다. 글을 새로 작성해주세요!");
                        continue;
                    }
                    System.out.print("삭제할 글의 idx 번호를 입력해주세요. (띄어써서 입력): ");

                    Scanner scanner1 = new Scanner(System.in);
                    String temp = scanner1.nextLine();
                    String[] arr = temp.split(" ");
                    if(this.deletePost(arr)){
                        System.out.println("다중 삭제 성공");
                    }
                    else{
                        System.out.println("다중 삭제 실패");
                    }


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
            }



        } catch (SQLException e) {
            System.out.println("DB 연결에 실패하였습니다.");
            throw new RuntimeException(e);
        } finally {
            try{
                assert rs != null;
                rs.close();
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }

    //상세 조회
    @Override
    public void readPost(int nb_board) {
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        Scanner scanner = new Scanner(System.in);
        int inputData;

        try{
            //조회수 증가
            String updateSql = "UPDATE TB_BOARD SET CN_HIT = CN_HIT+1 WHERE NB_BOARD="+nb_board;
            pstmt = this.connection.prepareStatement(updateSql);
            int rows = pstmt.executeUpdate();

            //게시글 조회
            String sql = "SELECT B.NB_BOARD, B.NM_TITLE, B.NM_CONTENT, B.NM_WRITER, B.DA_WRITER, B.CN_HIT, C.NM_ORG_FILE " +
                    "FROM TB_BOARD B LEFT JOIN TB_CONTENT C ON B.ID_FILE = C.ID_FILE " +
                    "WHERE B.NB_BOARD = ? " +
                    "ORDER BY B.NB_BOARD DESC";


            pstmt = this.connection.prepareStatement(sql);

            pstmt.setInt(1, nb_board);
            rs = pstmt.executeQuery();
            System.out.println("----------------------------------------------------");
            while(rs.next()){
                System.out.println("글 번호 : "+ rs.getInt(1) +" | "+ "제목: "+ rs.getString(2)
                        +" | "+ "작성자: "+ rs.getString(4) +" | "+ "작성일: "+ rs.getString(5)
                        +" | "+ "조회수: "+rs.getInt(6)+" | "+ "파일 유무: "+rs.getString(7));
                System.out.println(rs.getString(3));
            }



            System.out.println("----------------------------------------------------");
            System.out.println("1. 글 수정 2. 글 삭제 3. 메인메뉴로 이동");
            System.out.print("선택 > ");
            inputData = scanner.nextInt();
            if(inputData==1){
                if(updatePost(nb_board)){
                    System.out.println("성공적으로 수정 되었습니다.");
                }
                else{
                    System.out.println("수정 실패!");
                }


            }
            //글 삭제
            else if(inputData==2){
                if(deletePost(nb_board)){
                    System.out.println("성공적으로 게시글을 삭제했습니다.");
                }
                else{
                    System.out.println("게시글 삭제 실패!");
                }


            }
            //메인 메뉴로 이동
            else if(inputData==3){
                return;
            }
            else{
                System.out.println("잘못 입력하셨습니다.");
            }

        } catch (SQLException e) {
            System.out.println("DB 연결에 실패하였습니다.");
            throw new RuntimeException(e);
        } finally {
            try{
                assert rs != null;
                rs.close();
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    // 글 작성
    @Override
    public boolean insertPost() {

        PreparedStatement pstmt=null;
        String isFile;
        String fileName="";
        String fileExt="";
        String idFile="";
        int flag = 0;
        int rows1=0;
        int rows2 =0;

        TbBoard tbBoard = new TbBoard();
        Scanner scanner = new Scanner(System.in);

        System.out.print("제목: ");
        tbBoard.setNmTitle(scanner.nextLine());

        System.out.print("작성자: ");
        tbBoard.setNmWriter(scanner.nextLine());

        System.out.print("파일 저장하십니까?(Y/N): ");

        isFile = scanner.nextLine();
        if(isFile.equalsIgnoreCase("N")){
            tbBoard.setIdFile("NULL");
            System.out.println("파일을 추가하지 않습니다.");
        }
        else{
            System.out.print("img 폴더에 존재하는 파일명을 입력하세요.");
            fileName = scanner.nextLine();
            if(isFileExist(fileName)){
                flag = 1;
            }
        }
        System.out.println("작성후 EOF를 입력하세요");
        System.out.print("글 작성 > ");
        tbBoard.setNmContent(multiLineStatement(scanner));


        try {
            String sql = "INSERT INTO TB_BOARD(NB_BOARD, NM_TITLE, NM_CONTENT,NM_WRITER, DA_WRITER, CN_HIT,ID_FILE)\n" +
                    "VALUES(seq_tb_board.nextval, ?,?,?,SYSDATE,?,?)";
            pstmt = this.connection.prepareStatement(sql);
            pstmt.setString(1,tbBoard.getNmTitle());
            pstmt.setString(2,tbBoard.getNmContent());
            pstmt.setString(3,tbBoard.getNmWriter());
            pstmt.setInt(4, tbBoard.getCnHit());

            // 파일추가를 했으면 파일도 저장
            if(flag==1){
                idFile = UUID.randomUUID().toString().substring(0,20);
                pstmt.setString(5,idFile);
            }
            else{
                pstmt.setNull(5, Types.VARCHAR);
            }
            rows1 = pstmt.executeUpdate();


            if(flag==1){
                //tb_content에 실제 파일 추가
                // 파일ID, 원본 파일명, 저장 파일, 파일 확장자명, 저장 일시, 조회수
                String sql2 = "INSERT INTO TB_CONTENT(ID_FILE, NM_ORG_FILE, BO_SAVE_FILE, NM_FILE_EXT, DA_SAVE, CN_HIT)"
                        +"VALUES (?,?,?,?,SYSDATE,?)";
                pstmt = this.connection.prepareStatement(sql2);
                pstmt.setString(1,idFile);
                pstmt.setString(2, fileName);
                pstmt.setBlob(3, new FileInputStream("./files/"+fileName));



                int idx = fileName.lastIndexOf(".");
                if(idx>0){
                    fileExt = fileName.substring(idx+1);
                    System.out.println("파일 확장자: "+fileExt);
                }
                pstmt.setString(4, fileExt);
                pstmt.setInt(5, 0);
                rows2 = pstmt.executeUpdate();
            }
            return (rows1 > 0 && rows2 > 0);

        } catch (SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                assert pstmt != null;
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }

    }

    @Override
    public boolean updatePost(int nb_board) {
        PreparedStatement pstmt=null;
        String isContainFile="";
        int flag = 0;
        int rows = 0;
        String fileExt="";
        String idFile = null;
        String fileName= null;
        Scanner scanner = new Scanner(System.in);

        // 수정할 번호로 update문 실행
        System.out.print("제목: ");
        String nmTitle = scanner.nextLine();

        System.out.println("작성후 EOF를 입력하세요");
        System.out.print("글 작성 > ");
        String nmContent = multiLineStatement(scanner);

        try{
            String sql = "UPDATE TB_BOARD SET "
                    +"NM_TITLE=?, "
                    +"NM_CONTENT=?,"
                    +"DA_WRITER=SYSDATE, "
                    +"CN_HIT =CN_HIT+1"
                    +"WHERE NB_BOARD="+nb_board;
            pstmt = this.connection.prepareStatement(sql);
            pstmt.setString(1,nmTitle);
            pstmt.setString(2,nmContent);

            rows = pstmt.executeUpdate();

            //파일 업데이트
            return rows > 0 && updateFile(nb_board, pstmt);

        }catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                assert pstmt != null;
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean deletePost(int nb_board) {
        int rows = 0;
        PreparedStatement pstmt=null;

        String sql1 = "DELETE FROM TB_BOARD WHERE NB_BOARD = ?";
        String sql2 ="DELETE FROM TB_CONTENT " +
                "WHERE ID_FILE = ( " +
                "    SELECT ID_FILE " +
                "    FROM TB_BOARD " +
                "    WHERE NB_BOARD = ?"+
                ")";
        //tb_board 삭제, 이때 파일이 존재한다면 tb_content까지 삭제

        try {

            // 파일도 있다면
            if(isPostContainFile(nb_board)){
                pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, nb_board);
                rows = pstmt.executeUpdate();
            }
            //tb_board 삭제
            pstmt = connection.prepareStatement(sql1);
            pstmt.setInt(1, nb_board);
            rows = pstmt.executeUpdate();

            return rows>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                assert pstmt != null;
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean deletePost(String[] arr) {
        for (String s : arr) {
            if (!deletePost(Integer.parseInt(s)))
                return false;
        }
        return true;
    }


    public static String multiLineStatement(Scanner sc) {
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            // 사용자가 "EOF"를 입력하면 반복을 종료
            if ("EOF".equals(line)) {
                break;
            }
            // 입력받은 줄을 StringBuilder 객체에 추가
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
    //DB Connection close
    public void dbClose(){
        DbConnection.dbUnconnected(this.connection);
    }
    //비밀번호 일치 여부 확인
    public boolean updateFile(int nb_board, PreparedStatement pstmt){
        String idFile = "";
        String fileName;
        int flag = 0;
        int rows1 = 0;
        int rows2 = 0;
        String fileExt = null;

        System.out.print("파일을 업데이트 하십니까?(Y/N): ");
        String answer = scanner.nextLine();

        //기존 파일 유지
        if(answer.equalsIgnoreCase("N")){
            System.out.println("파일을 업데이트 하지 않습니다.");
        }
        // 새로운 파일로 업데이트
        else{
            idFile = UUID.randomUUID().toString().substring(0,20);

            System.out.print("img 폴더에 존재하는 파일명을 입력하세요.");
            fileName = scanner.nextLine();

            // file 존재 여부 확인
            if(!isFileExist(fileName)){
                return false;
            }

            //TB_BOARD query
            String sql1 = "UPDATE TB_BOARD SET "
                    +"ID_FILE=?"
                    +"WHERE NB_BOARD="+nb_board;

            //TB_CONTENT query
            String sql2 = "INSERT INTO TB_CONTENT(ID_FILE, NM_ORG_FILE, BO_SAVE_FILE, NM_FILE_EXT, DA_SAVE, CN_HIT)"
                    +"VALUES (?,?,?,?,SYSDATE,?)";
            try{
                //TB_BOARD update -> UPDATE FK
                pstmt = this.connection.prepareStatement(sql1);
                pstmt.setString(1, idFile);
                rows1 = pstmt.executeUpdate();


                int idx = fileName.lastIndexOf(".");
                if(idx>0){
                    fileExt = fileName.substring(idx+1);
                    System.out.println("파일 확장자: "+fileExt);
                }

                //TB_CONTENT update -> INSERT
                pstmt = this.connection.prepareStatement(sql2);
                pstmt.setString(1,idFile);
                pstmt.setString(2, fileName);
                pstmt.setBlob(3, new FileInputStream("./files/"+fileName));
                pstmt.setString(4, fileExt);
                pstmt.setInt(5, 0);

                rows2 = pstmt.executeUpdate();

                return (rows1>0 && rows2>0);
            } catch (SQLException | FileNotFoundException e) {
                throw new RuntimeException(e);
            }finally {
                try{
                    assert pstmt != null;
                    pstmt.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
        }
        return false;
    }
    public boolean isPostContainFile(int nb_board){
        PreparedStatement pstmt=null;
        ResultSet rs = null;
        try{
            String sql ="SELECT COUNT(ID_FILE) " +
                    "FROM TB_BOARD " +
                    "WHERE ID_FILE IS NOT NULL AND NB_BOARD="+nb_board;
            pstmt = this.connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
                int cnt = rs.getInt(1);
                if(cnt>0){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                assert pstmt != null;
                pstmt.close();
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }
    public boolean isFileExist(String fileName){
        File dir = new File("./files/");
        String[] files = dir.list();
        for(String file: files){
            if(file.equals(fileName)){
                System.out.println(fileName+"을 추가합니다.");
                return true;
            }
        }
        // 파일이 존재하지 않는다면 return
        System.out.println("파일이 존재하지 않습니다.");
        return false;
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
