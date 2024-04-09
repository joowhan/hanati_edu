package kr.co.kopo.eCommerce.controller;

import kr.co.kopo.product.ProductCrud;
import kr.co.kopo.util.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class EcommerceCrud {
    Connection connection  = null;
    Scanner scanner = null;
    EcommerceCrud(){
        try{
//            DbConnection.dbUnconnected(connection);
            this.connection = DbConnection.dbConnected();
            scanner = new Scanner(System.in);

        } catch (SQLException e) {
            System.out.println("DB 연결에 실패하였습니다.");
            throw new RuntimeException(e);
        }
    }
}
