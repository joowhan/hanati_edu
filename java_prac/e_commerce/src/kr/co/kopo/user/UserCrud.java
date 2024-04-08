package kr.co.kopo.user;

import kr.co.kopo.Crud;
import kr.co.kopo.DbConnection;
import kr.co.kopo.Validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserCrud implements Crud {
    Connection connection =null;
    Scanner scanner = null;
    UserCrud(){
        try{
            this.connection = DbConnection.dbConnected();
            scanner = new Scanner(System.in);
        } catch (SQLException e) {
            System.out.println("DB 연결에 실패하였습니다.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readData() {
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        int inputData = 0;
        List<String> tb_UserList = new ArrayList<>();
        String sql ="SELECT NO_USER, ID_USER, NM_USER, NM_PASWD, NM_EMAIL, ST_STATUS " +
                "FROM TB_USER " +
                "ORDER BY NO_USER ASC";


        try{
            while(true){
                pstmt = connection.prepareStatement(sql);
                rs = pstmt.executeQuery();
                System.out.println("------------------------------------------------------------------------------------------------------------------------");
                System.out.println("===== 사용자 목록 =====");
                System.out.printf("%-15s %-18s %-20s %-20s %-25s %-20s \n","사용자 번호","사용자 ID","사용자명","비밀번호","이메일","상태");
                System.out.println("------------------------------------------------------------------------------------------------------------------------");
                while(rs.next()){
                    System.out.printf("%-20s %-20s %-20s %-20s %-30s %-20s \n",
                            rs.getString(1),rs.getString(2),rs.getString(3),
                            rs.getString(4),rs.getString(5),rs.getString(6));

                    tb_UserList.add(rs.getString(1));
                }
                System.out.println("------------------------------------------------------------------------------------------------------------------------");
                System.out.println("1. 사용자 추가 2. 상세 조회 및 수정 3. (다중)삭제 4. 종료");
                System.out.print("선택 > ");
                inputData = scanner.nextInt();
                scanner.nextLine();
                //사용자 추가
                if(inputData==1){
                    if(insertData()){
                        System.out.println("사용자 추가 성공!");
                    }
                    else{
                        System.out.println("사용자 추가 실패");
                    }
                }
                // 사용자 정보 수정
                else if(inputData==2){
                    System.out.print("조회할 사용자 번호를 입력하세요: ");
                    String noUser = scanner.nextLine();
                    if(tb_UserList.contains(noUser)){
                        if(updateData(noUser)){
                            System.out.println("사용자 수정 성공!");
                        }
                        else{
                            System.out.println("사용자 수정 실패");
                        }
                    }
                    else{
                        System.out.println("사용자 번호가 존재하지 않습니다.");
                    }

                }
                // 사용자 삭제
                else if(inputData==3){
                    if(deleteData()){
                        System.out.println("사용자 삭제 성공!");
                    }
                    else{
                        System.out.println("사용자 삭제 실패");
                    }
                }
                //프로그램 종료
                else if(inputData==4){
                    System.out.println("프로그램 종료.");
//                    DbConnection.dbUnconnected(connection);
                    break;
                }
                else{
                    System.out.println("다시 입력하세요.");
                    System.out.println("------------------------------------------------------------------------------------------------------------------------");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                assert rs != null;
                rs.close();
                pstmt.close();
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean insertData() {
        String sql="INSERT INTO TB_USER(NO_USER, ID_USER, NM_USER, NM_PASWD, NM_EMAIL, ST_STATUS) " +
                "VALUES('PD' || LPAD(seq_tb_user.nextval, 5, '0'),?,?,?,?,'ST01')";
        PreparedStatement pstmt=null;
        int rows = 0;

        // 새롭게 id, pwd 생성
        TbUser tbUser = new TbUser();
//        System.out.println("뒤로가기: exit 입력");
        System.out.print("이름을 입력하세요: ");
        tbUser.setNmUser(scanner.nextLine());


        while(true){
            System.out.print("ID를 입력하세요: ");
            tbUser.setIdUser(scanner.nextLine());
            if(isUserExist(tbUser.getIdUser())){
                System.out.println("이미 아이디가 존재합니다.");
            }
            else if(Validation.idValidation(tbUser.getIdUser())){
                break;
            }


            System.out.println("아이디는 영문자, 숫자 사용 가능, 5~15자리여야 합니다.");
        }

        while(true){
            System.out.print("비밀번호를 입력하세요: ");
            tbUser.setNmPaswd(scanner.nextLine());
            if(Validation.pwdValidation(tbUser.getNmPaswd())){
                break;
            }
            System.out.println("비밀번호는 영문자(대문자/소문자 1 이상 포함), 숫자 1이상 포함, 5~15자리여야 합니다.");
        }

        while(true){
            System.out.print("이메일을 입력하세요: ");
            tbUser.setNmEmail(scanner.nextLine());
            if(Validation.emailValidation(tbUser.getNmEmail())){
                break;
            }
            System.out.println("이메일 형식을 확인하세요.");
        }

        try{
            pstmt = this.connection.prepareStatement(sql);
            pstmt.setString(1,tbUser.getIdUser());
            pstmt.setString(2, tbUser.getNmUser());
            pstmt.setString(3, tbUser.getNmPaswd());
            pstmt.setString(4, tbUser.getNmEmail());
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
    public boolean updateData(String noUser) {
        //사용자명, 비밀번호, 이메일 변경 가능
        // 객체에 불러와서 객체에서 변경한 다음  -> update  쿼리로 변경

        PreparedStatement pstmt=null;
        ResultSet rs = null;
        String sql1 ="SELECT NO_USER, ID_USER, NM_USER, NM_PASWD, NM_EMAIL, ST_STATUS " +
                "FROM TB_USER " +
                "WHERE NO_USER=? "+
                "ORDER BY NO_USER ASC";
        TbUser tbUser = new TbUser();
        try{
            pstmt = this.connection.prepareStatement(sql1);
            pstmt.setString(1,noUser);
            rs = pstmt.executeQuery();
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            while(rs.next()){
                System.out.printf("%-20s %-20s %-20s %-20s %-30s %-20s \n",
                        rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6));
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean deleteData(String[] arr) {
        return false;
    }

    public boolean isUserExist(String id_user){
        String sql ="select count(*) as T " +
                "from tb_user " +
                "where id_user = ?";
        int a =0;
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        try{
            pstmt = this.connection.prepareStatement(sql);
            pstmt.setString(1, id_user);
            rs = pstmt.executeQuery();

            while(rs.next()){
                a = rs.getInt(1);
            }
            if(a>0){
                return true;
            }
            else{
                return false;
            }
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
}