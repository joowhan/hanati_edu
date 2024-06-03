/*
 * 파일: Calc_Bonus_by_stmt_1.java
 * 작성일: 2024.05.31
 * 주제: 전체 고객 데이터를 Client load
 *
 * ******************** Transaction 단위 *********************
 * 1 Row Insert후  commit
 * 1 Row Insert를 위해 매번 Statement 객체 생성
 * insert 후 commit
 *
 * ************************* SQL 검증 *************************
 * 대상 데이터의 전체 ROWS 와 INSERT 된 ROWS 비교
 * */
package calc_bonus.statement;

import connection.JdbcConnectionServer;
import connection.JdbcConnectionVM;
import oracle.net.ano.SupervisorService;
import utils.ExceptionLog;
import utils.ValidattionVM;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calc_Bonus_by_stmt_1 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement stmt = null;
        Statement stmt_insert = null;
        ResultSet rs = null;
        // 변수 초기화
        String customerId = "";     // customer id
        String enrollDt = "";       // 등록 date
        int creditLimit = 0;        // 포인트 등급
        String address1 = "";       // 주소
        String email = "";          // email
        String gender = "";         // 성별
        String coupon = "";         // 쿠폰 코드
        int count = 0;              // 개수

        //truncate table sql
        String truncate_sql ="truncate table bonus_coupon ";

        // 전체 고객 데이터 조회
        String sql ="SELECT * FROM CUSTOMER ";
//                   + "WHERE ROWNUM <=100 "; // 100개의 데이터로 TEST
//                   + "AND ENROLL_DT >='2018-01-01'";// 날짜 TEST
        try{

            //시간 측정 시작
            long startTime = System.currentTimeMillis();
            /*
            * DBMS Connection
            * */
            // connection 연결 - 서버로 연결
            // connection = JdbcConnectionServer.dbConnected();

            // connection 연결 - 가상머신으로 연결
            connection = JdbcConnectionVM.dbConnected();

            //table truncate
            stmt = connection.createStatement();
            // query 실행
            stmt.executeUpdate(truncate_sql);
            System.out.println("table truncated!");

            // 전체 데이터를 clien로 load
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            // load한 데이터 조회
            while(rs.next()){
                //데이터 저장
                customerId = rs.getString(1);
                address1 = rs.getString(5);
                creditLimit = rs.getInt(9);
                email = rs.getString(10);
                enrollDt = rs.getString(13);
                gender = rs.getString(14);

                //2013-01-01 이후의 데이터만 대상 데이터로 선정
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date enrollDate = sdf.parse(enrollDt);
                Date targetDate = sdf.parse("2013-01-01 00:00:00");
                //2013-01-01 이후의 데이터, 만약 이전의 데이터라면 처리 생략
                if(enrollDate.before(targetDate))
                    continue;

                // 고객 별 보너스 계산
                if(creditLimit < 1000) //1000미만이면 AA
                    coupon = "AA";
                else if(creditLimit >=1000 && creditLimit <=2999) //1000과 3000 사이 = BB
                    coupon = "BB";
                else if(creditLimit>=3000 && creditLimit<=3999) { // 3000과 4000 사이
                    // 송파구 풍납 1동에 거주하는 여성 고객
                    if (address1.contains("송파구 풍납1동") && gender.equals("F"))
                        coupon = "C2";
                    // 아니면 CC
                    else
                        coupon ="CC";
                } else if (creditLimit >=4000) { // 4000이상이면 DD
                    coupon ="DD";
                }

                // BONUS_COUPON에 insert
                String sql2 = "INSERT INTO BONUS_COUPON(YYYYMM, CUSTOMER_ID, EMAIL, COUPON, CREDIT_POINT, SEND_DT) " +
                        "VALUES ('202406','"+customerId+"','"+ email+"','"+coupon+"',"+ creditLimit+", SYSDATE)";
                /*
                ***********Transaction 단위***********
                * 1 Row Insert후  commit
                * 1 Row Insert를 위해 매번 Statement 객체 생성
                * insert 후 commit
                * ************************************
                 * */
                stmt_insert = connection.createStatement();
                stmt_insert.executeQuery(sql2);
                // 반납
                stmt_insert.close();
                // commit
                connection.commit();
                //데이터 처리 수 증가
                count ++;

                // 진행 상황 체크
                if(count%100000==0)
                    System.out.println(count + "개 row가 insert 되었습니다...");
            }
            // 시간 측정 종료
            long endTime = System.currentTimeMillis();
            // 처리한 row 수
            System.out.println("*********************************************************************************************************");
            System.out.println("총 "+count+" rows 처리 성공, 경과 시간: "+(endTime-startTime)+"ms");

            // 검증 process
            int inserted = ValidattionVM.insertValidation();
            System.out.println("실제 insert된 rows: "+inserted);
            ValidattionVM.countBonusGroup();
            System.out.println("*********************************************************************************************************");

        } catch (SQLException e) {
            try {
                if (connection !=null)
                    connection.rollback();
                System.out.println("[EXCEPTION]: SQL exception: "+e.getMessage());
                // 예외를 로그 파일에 기록
                ExceptionLog.writeLog(e, Calc_Bonus_by_stmt_1.class.getName());
            } catch (SQLException ex) {
                System.out.println("[EXCEPTION]: SQL exception: "+ex.getMessage());
                // 예외를 로그 파일에 기록
                ExceptionLog.writeLog(ex, Calc_Bonus_by_stmt_1.class.getName());
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            // 예외를 로그 파일에 기록
            ExceptionLog.writeLog(e, Calc_Bonus_by_stmt_1.class.getName());
            System.out.println("[EXCEPTION]: class not found exception: "+e.getMessage());
            throw new RuntimeException(e);
        } catch (ParseException e) {
            // 예외를 로그 파일에 기록
            ExceptionLog.writeLog(e, Calc_Bonus_by_stmt_1.class.getName());
            System.out.println("[EXCEPTION]: parse exception: "+e.getMessage());
            throw new RuntimeException(e);
        } finally {
            try{
                if(rs !=null) rs.close();
                if(stmt != null) stmt.close();
                connection.close();

            } catch (SQLException e) {
                // 예외를 로그 파일에 기록
                ExceptionLog.writeLog(e, Calc_Bonus_by_stmt_1.class.getName());
                e.printStackTrace();
            }
        }

    }
}
