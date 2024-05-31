/*
 * 파일: Calc_Bonus_by_callstmt_2.java
 * 작성일: 2024.05.31
 * 주제: PL/SQL을 활용해 쿼리를 dbms 서버로 전송
 *       bulk collect, forall을 적용해서 1000 개 단위로 처리
 * ******************** Transaction 단위 *********************
 * PL/SQL을 활용
 * anonymous block으로 쿠폰 계산
 * bulk collect, forall을 적용해서 1000 개 단위로 처리
 *  빈번한 sql 실행 개선
 * ************************* SQL 검증 *************************
 * 대상 데이터의 전체 ROWS 와 INSERT 된 ROWS 비교
 * */
package calc_bonus.callableStatement;

import connection.JdbcConnectionVM;
import utils.ExceptionLog;
import utils.ValidattionVM;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Calc_Bonus_by_callstmt_2 {
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
                "    CURSOR CUR_CUSTOMER IS\n" + //cursor 정의
                "        SELECT ID, EMAIL, CREDIT_LIMIT, ADDRESS1, ENROLL_DT, GENDER\n" +
                "        FROM CUSTOMER\n" +
                "        WHERE ENROLL_DT >='2013/01/01';\n" +
                "    \n" +
                "    TYPE T_RECORD_CUSTOMER IS TABLE OF CUR_CUSTOMER%ROWTYPE INDEX BY BINARY_INTEGER;\n" + // cur_customer array
                "    TYPE T_BONUS IS TABLE OF VARCHAR2(2) INDEX BY BINARY_INTEGER;\n" + // bonus 계산 저장 array
                "    \n" +
                "    R_CUR_CUSTOMER      T_RECORD_CUSTOMER;\n" +
                "    V_BONUS             T_BONUS;\n" +
                "    V_ARRAYSIZE         NUMBER(5) := 1000;\n" + // array size 1000
                "    V_COUNT             NUMBER(10) :=0;\n" +
                "BEGIN\n" +
                "    OPEN CUR_CUSTOMER;\n" + //cursor open
                "    LOOP\n" +
                "        FETCH CUR_CUSTOMER BULK COLLECT INTO R_CUR_CUSTOMER LIMIT V_ARRAYSIZE;\n" + //cursor bulk collect로 fetch
                "        EXIT WHEN R_CUR_CUSTOMER.COUNT = 0;\n" +
                "        \n" +
                "        -- V_BONUS 값 설정. 계산 \n" +
                "        FOR I IN R_CUR_CUSTOMER.FIRST .. R_CUR_CUSTOMER.LAST LOOP\n" +
                "            IF R_CUR_CUSTOMER(I).CREDIT_LIMIT < 1000 THEN\n" +
                "                V_BONUS(I) := 'AA';\n" +
                "            ELSIF R_CUR_CUSTOMER(I).CREDIT_LIMIT BETWEEN 1000 AND 2999 THEN\n" +
                "                V_BONUS(I) := 'BB';\n" +
                "            ELSIF R_CUR_CUSTOMER(I).CREDIT_LIMIT BETWEEN 3000 AND 3999 THEN\n" +
                "                IF R_CUR_CUSTOMER(I).ADDRESS1 LIKE '%송파구 풍납1동%' AND R_CUR_CUSTOMER(I).GENDER = 'F' THEN\n" +
                "                    V_BONUS(I) := 'C2';\n" +
                "                ELSE\n" +
                "                    V_BONUS(I) := 'CC';\n" +
                "                END IF;\n" +
                "            ELSIF R_CUR_CUSTOMER(I).CREDIT_LIMIT >= 4000 THEN\n" +
                "                V_BONUS(I) := 'DD';\n" +
                "            ELSE\n" +
                "                V_BONUS(I) := NULL;\n" +
                "            END IF;\n" +
                "        END LOOP;\n" +
                "        \n" +
                "        -- FORALL 문을 사용하여 INSERT\n" +
                "        FORALL I IN R_CUR_CUSTOMER.FIRST .. R_CUR_CUSTOMER.LAST\n" +
                "            INSERT INTO BONUS_COUPON(YYYYMM, CUSTOMER_ID, EMAIL, COUPON, CREDIT_POINT, SEND_DT)\n" +
                "            VALUES ('202406', \n" +
                "                    R_CUR_CUSTOMER(I).ID, \n" +
                "                    R_CUR_CUSTOMER(I).EMAIL, \n" +
                "                    V_BONUS(I), \n" +
                "                    R_CUR_CUSTOMER(I).CREDIT_LIMIT, \n" +
                "                    SYSDATE);\n" +
                "        V_COUNT := V_COUNT+1;\n" +
                "            IF MOD(V_COUNT,10)=0 THEN\n" +
                "                COMMIT;\n" +
                "            END IF;\n" +
                "    END LOOP;\n" +
                "    \n" +
                "    DBMS_OUTPUT.PUT_LINE('TOTAL ' || TO_CHAR(CUR_CUSTOMER%ROWCOUNT) || ' ROWS PROCESSED');\n" +
                "    CLOSE CUR_CUSTOMER;\n" +
                "    COMMIT;\n" +
                "    \n" +
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
            ExceptionLog.writeLog(e, Calc_Bonus_by_callstmt_2.class.getName());
            System.out.println("[EXCEPTION]: class not found exception: "+e.getMessage());
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            ExceptionLog.writeLog(e, Calc_Bonus_by_callstmt_2.class.getName());
            System.out.println("[EXCEPTION]: class not found exception: "+e.getMessage());
            throw new RuntimeException(e);
        }  finally {
            try{
                if(cstmt != null) cstmt.close();
                if(pstmt != null) pstmt.close();
                connection.close();

            } catch (SQLException e) {
                ExceptionLog.writeLog(e, Calc_Bonus_by_callstmt_2.class.getName());
                System.out.println("[EXCEPTION]: class not found exception: "+e.getMessage());
                e.printStackTrace();
            }
        }

    }
}
