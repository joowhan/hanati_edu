/*
 * 파일: Calc_Bonus_by_callstmt_1.java
 * 작성일: 2024.05.31
 * 주제: PL/SQL을 활용해 쿼리를 dbms 서버로 전송
 * ******************** Transaction 단위 *********************
 * PL/SQL을 활용
 * anonymous block으로 쿠폰 계산
 * 1 row 단위 fetch, insert
 * 10000 단위 commit
 * ************************* SQL 검증 *************************
 * 대상 데이터의 전체 ROWS 와 INSERT 된 ROWS 비교
 * */
package calc_bonus.callableStatement;

import calc_bonus.preparedStatement.Calc_Bonus_by_pstmt_1;
import connection.JdbcConnectionVM;
import utils.ExceptionLog;
import utils.ValidattionVM;

import java.sql.*;

public class Calc_Bonus_by_callstmt_1 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        CallableStatement cstmt = null;


        //truncate table sql
        String truncate_sql ="truncate table bonus_coupon ";

        // PL/SQL
        // PL/SQL로 대상 데이터 조회 + 보너스 계산 + insert
        String sql ="DECLARE\n" +
                "    -- 2013년 이후 데이터\n" +
                "    CURSOR CUR_CUSTOMER IS\n" + //커서 정의
                "        SELECT ID, EMAIL, CREDIT_LIMIT, ADDRESS1, ENROLL_DT, GENDER\n" +
                "        FROM CUSTOMER\n" +
                "        WHERE ENROLL_DT >='2013/01/01';\n" +
                "    \n" +
                "    V_BONUS             CHAR(2);\n" + // 보너스 계산
                "    R_CUR_CUSTOMER      CUR_CUSTOMER%ROWTYPE;\n" + //cur_customer rowtype
                "    V_COUNT             NUMBER(10) :=0;\n" + //처리 쿼리 개수
                "    \n" +
                "BEGIN\n" +
                "    OPEN CUR_CUSTOMER;\n" + //cursor open
                "    LOOP\n" +
                "        FETCH CUR_CUSTOMER INTO R_CUR_CUSTOMER;\n" + //fetch
                "        EXIT WHEN CUR_CUSTOMER%NOTFOUND;\n" + // loop exit 조건
                "        \n" +
                "        -- V_BONUS 값 설정, 계산\n" +
                "            IF R_CUR_CUSTOMER.CREDIT_LIMIT < 1000 THEN\n" +
                "                V_BONUS := 'AA';\n" +
                "            ELSIF R_CUR_CUSTOMER.CREDIT_LIMIT BETWEEN 1000 AND 2999 THEN\n" +
                "                V_BONUS := 'BB';\n" +
                "            ELSIF R_CUR_CUSTOMER.CREDIT_LIMIT BETWEEN 3000 AND 3999 THEN\n" +
                "                IF R_CUR_CUSTOMER.ADDRESS1 LIKE '%송파구 풍납1동%' AND R_CUR_CUSTOMER.GENDER = 'F' THEN\n" +
                "                    V_BONUS := 'C2';\n" +
                "                ELSE\n" +
                "                    V_BONUS := 'CC';\n" +
                "                END IF;\n" +
                "            ELSIF R_CUR_CUSTOMER.CREDIT_LIMIT >= 4000 THEN\n" +
                "                V_BONUS := 'DD';\n" +
                "            ELSE\n" +
                "                V_BONUS := NULL;\n" +
                "            END IF;\n" +
                "        \n" + // bonus insert
                "            INSERT INTO BONUS_COUPON(YYYYMM, CUSTOMER_ID, EMAIL, COUPON, CREDIT_POINT, SEND_DT)\n" +
                "            VALUES ('202406', \n" +
                "                    R_CUR_CUSTOMER.ID, \n" +
                "                    R_CUR_CUSTOMER.EMAIL, \n" +
                "                    V_BONUS, \n" +
                "                    R_CUR_CUSTOMER.CREDIT_LIMIT, \n" +
                "                    SYSDATE);\n" +
                "            V_COUNT := V_COUNT+1;\n" +
                "            IF MOD(V_COUNT,10000)=0 THEN\n" +
                "                COMMIT;\n" +
                "            END IF;\n" +
                "    END LOOP;\n" +
                "    COMMIT;\n" +
                "    DBMS_OUTPUT.PUT_LINE('TOTAL ' || TO_CHAR(CUR_CUSTOMER%ROWCOUNT) || ' ROWS PROCESSED');\n" +
                "    CLOSE CUR_CUSTOMER;\n" +
                "END; " ;

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
            // plsql 실행
            cstmt = connection.prepareCall(sql);
            cstmt.execute();

            // 시간 측정 종료
            long endTime = System.currentTimeMillis();
            // 처리한 row 수
            System.out.println("*********************************************************************************************************");
            System.out.println("경과 시간: "+(endTime-startTime)+"ms");

            // 검증 process
            int inserted = ValidattionVM.insertValidation();
            System.out.println("실제 insert된 rows: "+inserted);
            ValidattionVM.countBonusGroup();
            System.out.println("*********************************************************************************************************");

        } catch (SQLException e) {
            ExceptionLog.writeLog(e, Calc_Bonus_by_callstmt_1.class.getName());
            System.out.println("[EXCEPTION]: class not found exception: "+e.getMessage());
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            ExceptionLog.writeLog(e, Calc_Bonus_by_callstmt_1.class.getName());
            System.out.println("[EXCEPTION]: class not found exception: "+e.getMessage());
            throw new RuntimeException(e);
        }  finally {
            try{
                if(cstmt != null) cstmt.close();
                if(pstmt != null) pstmt.close();
                connection.close();

            } catch (SQLException e) {
                ExceptionLog.writeLog(e, Calc_Bonus_by_callstmt_1.class.getName());
                System.out.println("[EXCEPTION]: class not found exception: "+e.getMessage());
                e.printStackTrace();
            }
        }

    }
}
