/*
 * 파일: Calc_Bonus_by_pstmt_1.java
 * 작성일: 2024.05.31
 * 주제: 대상 고객 데이터를 Client load, PreparedStatement 객체 사용
 *      빈번한 hard parsing을 개선
 * ******************** Transaction 단위 *********************
 * preparedStatement 객체를 1번만 생성하고 재사용 후 리소스 반납
 * WHERE 절을 추가해서 SQL을 실행, 기존의 필터링 알고리즘 삭제
 * 10000건 INSERT 후 COMMIT
 * fetch size를 1000으로 변경, default는 10 -> 빈번한 fetch 개선
 * ************************* SQL 검증 *************************
 * 대상 데이터의 전체 ROWS 와 INSERT 된 ROWS 비교
 * */
package calc_bonus.preparedStatement;

import calc_bonus.statement.Calc_Bonus_by_stmt_5;
import connection.JdbcConnectionVM;
import utils.ExceptionLog;
import utils.ValidattionVM;

import java.sql.*;

public class Calc_Bonus_by_pstmt_1 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt_insert = null;
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

        // 대상 고객 데이터 조회
        // "WHERE ENROLL_DT >='2013/01/01'" 추가 2013년 이후 가입고객만 조회
        String sql ="SELECT * FROM CUSTOMER " +
                    "WHERE ENROLL_DT >='2013/01/01'";
//                   + "WHERE ROWNUM <=100 "; // 100개의 데이터로 TEST
        // BONUS_COUPON에 insert
        String sql2 = "INSERT INTO BONUS_COUPON(YYYYMM, CUSTOMER_ID, EMAIL, COUPON, CREDIT_POINT, SEND_DT) " +
                "VALUES ('202406','"+customerId+"','"+ email+"','"+coupon+"',"+ creditLimit+", SYSDATE)";
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
            pstmt = connection.prepareStatement(truncate_sql);
            // query 실행
            pstmt.executeUpdate();
            System.out.println("table truncated!");

            // 전체 데이터를 clien로 load
            pstmt = connection.prepareStatement(sql);

            /*
            * fetch size 변경
            * fetch size 크기를 조정해서 빈번한 fetch 연산을 개선
            * */
            pstmt.setFetchSize(1000);

            // 객체를 1번만 생성 후 재사용
            pstmt_insert = connection.prepareStatement(sql2);

            // select 쿼리 실행, 대상 데이터 조회
            rs = pstmt.executeQuery();
            // load한 데이터 조회
            while(rs.next()){
                //데이터 저장
                customerId = rs.getString(1);
                address1 = rs.getString(5);
                creditLimit = rs.getInt(9);
                email = rs.getString(10);
                enrollDt = rs.getString(13);
                gender = rs.getString(14);


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

                /*
                 * ******************** Transaction 단위 *********************
                 * preparedStatement 객체를 1번만 생성하고 재사용 후 리소스 반납
                 * WHERE 절을 추가해서 SQL을 실행, 기존의 필터링 알고리즘 삭제
                 * 10000건 INSERT 후 COMMIT
                 * fetch size를 1000으로 변경, default는 10 -> 빈번한 fetch 개선
                 * ***********************************************************
                 * */
                pstmt_insert.executeQuery();

                // 10000건 단위로 commit -> 빈번한 commit 개선 
                if(count%10000==0)
                    connection.commit();
                //데이터 처리 수 증가
                count ++;

                // 진행 상황 체크
                if(count%100000==0)
                    System.out.println(count + "개 row가 insert 되었습니다...");
            }

            // 최종 commit
            connection.commit();

            // 반납
            if(pstmt_insert !=null)
                pstmt_insert.close();
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
            ExceptionLog.writeLog(e, Calc_Bonus_by_pstmt_1.class.getName());
            System.out.println("[EXCEPTION]: class not found exception: "+e.getMessage());
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            ExceptionLog.writeLog(e, Calc_Bonus_by_pstmt_1.class.getName());
            System.out.println("[EXCEPTION]: class not found exception: "+e.getMessage());
            throw new RuntimeException(e);
        }  finally {
            try{
                if(rs !=null) rs.close();
                if(pstmt != null) pstmt.close();
                connection.close();

            } catch (SQLException e) {
                ExceptionLog.writeLog(e, Calc_Bonus_by_pstmt_1.class.getName());
                System.out.println("[EXCEPTION]: class not found exception: "+e.getMessage());
                e.printStackTrace();
            }
        }

    }
}
