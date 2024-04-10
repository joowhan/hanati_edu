package kr.co.kopo.product;

import kr.co.kopo.util.DbConnection;
import kr.co.kopo.product.model.TbProduct;
import kr.co.kopo.util.ProductCrudInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ProductCrud implements ProductCrudInterface {
    Connection connection =null;
    Scanner scanner = null;
    private static LinkedHashMap<String, TbProduct> tbProductList = null;
    public ProductCrud(){
        try{
//            DbConnection.dbUnconnected(connection);
            this.connection = DbConnection.dbConnected();
            scanner = new Scanner(System.in);
            setTbProductList();

        } catch (SQLException e) {
            System.out.println("DB 연결에 실패하였습니다.");
            throw new RuntimeException(e);
        }
    }
    public LinkedHashMap<String, TbProduct> getTbProductList(){
        setTbProductList();
        return tbProductList;
    }

    public void setTbProductList(){
        tbProductList = new LinkedHashMap<>();
        ResultSet rs = null;
        PreparedStatement pstmt=null;

        String sql ="SELECT NO_PRODUCT, NM_PRODUCT, NM_DETAIL_EXPLAIN,DT_START_DATE, ID_FILE, DT_END_DATE, QT_SALE_PRICE, QT_STOCK, QT_DELIVERY_FEE " +
                "FROM TB_PRODUCT " +
                "ORDER BY NO_PRODUCT DESC";
        try{
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                TbProduct tbProduct = new TbProduct();

                tbProduct.setNoProduct(rs.getString(1));
                tbProduct.setNmProduct(rs.getString(2));
                tbProduct.setNmDetailExplain(rs.getString(3));
                tbProduct.setDtStartDate(rs.getString(4));
                tbProduct.setIdFile(rs.getString(5));
                tbProduct.setDtEndDate(rs.getString(6));
                tbProduct.setQtCustomer(rs.getInt(7));
                tbProduct.setQtSalePrice(rs.getInt(7));
                tbProduct.setQtStock(rs.getInt(8));
                tbProduct.setQtDeliveryFee(rs.getInt(9));

                tbProductList.put(tbProduct.getNoProduct(), tbProduct);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if(rs != null){
                    rs.close();
                    pstmt.close();
                }

            }catch (SQLException e){
                e.printStackTrace();
            }

        }

    }

    @Override
    public void readData(int nbCategory) {
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        int inputData = 0;

        String sql ="SELECT P.NO_PRODUCT, P.NM_PRODUCT, C.NM_ORG_FILE, P.QT_SALE_PRICE, P.QT_DELIVERY_FEE, M.NB_CATEGORY " +
                "FROM TB_PRODUCT P LEFT OUTER JOIN TB_CONTENT C ON P.ID_FILE = C.ID_FILE JOIN tb_category_product_mapping M ON M.NO_PRODUCT = P.NO_PRODUCT " +
                "WHERE M.NB_CATEGORY = ?"+
                "ORDER BY P.NO_PRODUCT DESC";

        try{
            while(true){
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, nbCategory);
                rs = pstmt.executeQuery();
                System.out.println("------------------------------------------------------------------------------------------------------------------------");
                System.out.println("===== 사용자 목록 =====");
                System.out.printf("%-15s %-18s %-20s %-20s %-20s \n","상품 코드","상품 명","IMG","판매가격","배송비");
                System.out.println("------------------------------------------------------------------------------------------------------------------------");
                while(rs.next()){
                    System.out.printf("%-20s %-20s %-20s %-20s %-20s \n",
                            rs.getString(1),rs.getString(2),rs.getString(3),
                            rs.getString(4),rs.getString(5));

                }
                //만약 조회된 파일이 없다면 JSON에서 불러와서 채워넣기 -> 초기화
//                if(tbProductList.isEmpty()){
//                    ConvertJsonToDb convertJsonToDb = new ConvertJsonToDb();
//                }

                System.out.println("------------------------------------------------------------------------------------------------------------------------");
                System.out.println("1. "+nbCategory+"번 카테고리 상품 추가 2. 상세 조회 및 수정 3. 삭제 4. 종료");
                System.out.print("선택 > ");
                try{
                    inputData = scanner.nextInt();
                    scanner.nextLine();
                    //사용자 추가
                    if(inputData==1){
                        if(insertData(nbCategory)){
                            System.out.println("상품 추가 성공!");
                        }
                        else{
                            System.out.println("상품 추가 실패");
                        }
                    }
                    // 사용자 정보 수정
                    else if(inputData==2){
                        System.out.print("조회할 사용자 번호를 입력하세요: ");
                        String noProduct = scanner.nextLine();
                        if(tbProductList.containsKey(noProduct)){
                            if(updateData(nbCategory, noProduct)){
                                System.out.println("상품 수정 성공!");
                            }
                            else{
                                System.out.println("상품 수정 실패");
                            }
                        }
                        else{
                            System.out.println("상품  번호가 존재하지 않습니다.");
                        }

                    }
                    // 사용자 삭제
                    else if(inputData==3){
                        //입력 받기
                        if(tbProductList.size()==0){
                            System.out.println("상품이 없습니다. 상품을 새로 작성해주세요!");
                            continue;
                        }
                        System.out.print("삭제할 상품의 idx 번호를 입력해주세요.: ");
                        String docNums = scanner.nextLine();
//                        String[] arr = docNums.split(" ");

                        if(deleteData(nbCategory,docNums)){
                            System.out.println("상품 삭제 성공!");
                        }
                        else{
                            System.out.println("상품 삭제 실패");
                        }
                    }
                    //프로그램 종료
                    else if(inputData==4){
                        System.out.println("뒤로가기.");
//                    DbConnection.dbUnconnected(connection);
                        break;
                    }
                    else{
                        System.out.println("다시 입력하세요.");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------");
                    }
                } catch(InputMismatchException e){
                    System.out.println("정수만 입력 가능합니다. ");
                    break;
                }

            }
        } catch (SQLException e) {
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

    @Override
    public boolean insertData(int nbCategory) {
        // 파일을 저장하면 tb_content에서도 추가가 되어야 한다.
        // 매핑 테이블에도 동시에 추가되어야 한다.
        ResultSet rs=null;
        PreparedStatement pstmt=null;
        String isFile;
        String fileName="";
        String fileExt="";
        String idFile="";
        int flag = 0;
        int rows1=0;
        int rows2 =0;
        String sql1 = "select 'PT' || LPAD(seq_tb_product.nextval, 7, '0') AS " +
                    "no_product from dual ";
        String sql = "INSERT INTO TB_PRODUCT(NO_PRODUCT, NM_PRODUCT, NM_DETAIL_EXPLAIN, DT_START_DATE, DT_END_DATE, QT_CUSTOMER,QT_SALE_PRICE, QT_STOCK, QT_DELIVERY_FEE, ID_FILE) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";


        // 추가 요소 입력
        TbProduct tbProduct = new TbProduct();
        System.out.print("상품명: ");
        tbProduct.setNmProduct(scanner.nextLine());

        System.out.print("판매 시작일자(yyyy-MM-dd): ");
        tbProduct.setDtStartDate(scanner.nextLine());

        System.out.print("판매 종료일자(yyyy-MM-dd): ");
        tbProduct.setDtEndDate(scanner.nextLine());

        System.out.print("판매 가격: ");
        tbProduct.setQtSalePrice(scanner.nextInt());
        scanner.nextLine();

        System.out.print("판매 소비자 가격: ");
        tbProduct.setQtCustomer(scanner.nextInt());
        scanner.nextLine();

        System.out.print("재고 수량: ");
        tbProduct.setQtStock(scanner.nextInt());
        scanner.nextLine();

        System.out.print("배송비: ");
        tbProduct.setQtDeliveryFee(scanner.nextInt());
        scanner.nextLine();

        System.out.print("파일 저장하십니까?(Y/N): ");

        isFile = scanner.nextLine();
        if(isFile.equalsIgnoreCase("N")){
            tbProduct.setIdFile("NULL");
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
        System.out.print("상세설명 작성 > ");
        tbProduct.setNmDetailExplain(multiLineStatement(scanner));

        // 파일빼고 먼저 DB에 insert
        try{
            pstmt =  this.connection.prepareStatement(sql1);
            rs = pstmt.executeQuery();

            while(rs.next()){
                 tbProduct.setNoProduct(rs.getString(1));
            }

            pstmt = this.connection.prepareStatement(sql);

            pstmt.setString(1,tbProduct.getNoProduct());
            pstmt.setString(2,tbProduct.getNmProduct());
            pstmt.setString(3,tbProduct.getNmDetailExplain());
            pstmt.setDate(4, Date.valueOf(tbProduct.getDtStartDate()));
            pstmt.setDate(5, Date.valueOf(tbProduct.getDtEndDate()));
            pstmt.setInt(6,tbProduct.getQtCustomer());
            pstmt.setInt(7, tbProduct.getQtSalePrice());
            pstmt.setInt(8, tbProduct.getQtStock());
            pstmt.setInt(9, tbProduct.getQtDeliveryFee());

            if(flag==1){
                idFile = UUID.randomUUID().toString().substring(0,20);
                tbProduct.setIdFile(idFile);
                pstmt.setString(10,idFile);
            }
            else{
                pstmt.setNull(10, Types.VARCHAR);
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


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //카테고리 mapping insert
        insertMappingData(nbCategory, tbProduct.getNoProduct());
//        //파일 저장
//        tbProduct.setIdFile("");
//        idFile = updateFile(tbProduct.getIdFile(), tbProduct.getNoProduct());
//        tbProduct.setIdFile(idFile);
//
//        if(!idFile.equals(tbProduct.getIdFile())){
//            System.out.println("파일 저장 성공!");
//
//        }
        if(rows1>0 && rows2>0){
            tbProductList.put(tbProduct.getNoProduct(), tbProduct);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateData(int nbCategory, String key) {
        PreparedStatement pstmt=null;
        String isFile;
        String fileName="";
        String fileExt="";
        String idFile="";
        int flag = 0;
        int rows1=0;
        int rows2 =0;
        String sql = "UPDATE TB_PRODUCT SET "+
                "NM_PRODUCT=?, NM_DETAIL_EXPLAIN=?, DT_START_DATE=?, DT_END_DATE=?, QT_CUSTOMER=? ,QT_SALE_PRICE=?, QT_STOCK=?, QT_DELIVERY_FEE=? "+
                "WHERE NO_PRODUCT=?";

        TbProduct tbProduct = tbProductList.get(key);

        System.out.print("상품명: ");
        tbProduct.setNmProduct(scanner.nextLine());

        System.out.print("판매 시작일자(yyyy-MM-dd): ");
        tbProduct.setDtStartDate(scanner.nextLine());

        System.out.print("판매 종료일자(yyyy-MM-dd): ");
        tbProduct.setDtEndDate(scanner.nextLine());

        System.out.print("판매 가격: ");
        tbProduct.setQtSalePrice(scanner.nextInt());
        scanner.nextLine();

        System.out.print("판매 소비자 가격: ");
        tbProduct.setQtCustomer(scanner.nextInt());
        scanner.nextLine();

        System.out.print("재고 수량: ");
        tbProduct.setQtStock(scanner.nextInt());
        scanner.nextLine();

        System.out.print("배송비: ");
        tbProduct.setQtDeliveryFee(scanner.nextInt());
        scanner.nextLine();

        System.out.println("작성후 EOF를 입력하세요");
        System.out.print("상세설명 작성 > ");
        tbProduct.setNmDetailExplain(multiLineStatement(scanner));

        try{

            pstmt = this.connection.prepareStatement(sql);

            pstmt.setString(9,tbProduct.getNoProduct());
            pstmt.setString(1,tbProduct.getNmProduct());
            pstmt.setString(2,tbProduct.getNmDetailExplain());
            pstmt.setDate(3, Date.valueOf(tbProduct.getDtStartDate()));
            pstmt.setDate(4, Date.valueOf(tbProduct.getDtEndDate()));
            pstmt.setInt(5,tbProduct.getQtCustomer());
            pstmt.setInt(6, tbProduct.getQtSalePrice());
            pstmt.setInt(7, tbProduct.getQtStock());
            pstmt.setInt(8, tbProduct.getQtDeliveryFee());
            rows1 = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        idFile = updateFile(tbProduct.getIdFile(), tbProduct.getNoProduct());
        tbProduct.setIdFile(idFile);

        if(!idFile.equals(tbProduct.getIdFile())){
            System.out.println("파일 저장 성공!");

        }
        if(rows1>0 && rows2>0){
            tbProductList.put(tbProduct.getNoProduct(), tbProduct);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteData(int nbCategory, String[] keys) {
        return false;
    }

    @Override
    public boolean deleteData(int nbCategory, String key) {
        TbProduct tbProduct = tbProductList.get(key);
        int rows = 0;
        PreparedStatement pstmt=null;

        String sql = "DELETE FROM TB_PRODUCT WHERE NO_PRODUCT = ?";
        try {
            boolean delMapping = deleteMappingData(nbCategory,key);
            System.out.println(delMapping);

            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, tbProduct.getNoProduct());
            rows = pstmt.executeUpdate();
            if(delMapping && rows>0){
                tbProductList.remove(key);
                return true;
            }
            else return false;
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


    public void insertMappingData(int nbCategory, String noProduct){
        String sql="INSERT INTO TB_CATEGORY_PRODUCT_MAPPING(NB_CATEGORY, NO_PRODUCT) VALUES(?,?)";
        PreparedStatement pstmt=null;
        try{
            pstmt = this.connection.prepareStatement(sql);
            pstmt.setInt(1, nbCategory);
            pstmt.setString(2,noProduct);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }
    public boolean deleteMappingData(int nbCategory, String noProduct){
        String sql="DELETE FROM TB_CATEGORY_PRODUCT_MAPPING WHERE NB_CATEGORY=? AND NO_PRODUCT=?";
        PreparedStatement pstmt=null;
        try{
            pstmt = this.connection.prepareStatement(sql);
            pstmt.setInt(1, nbCategory);
            pstmt.setString(2,noProduct);
            int rows = pstmt.executeUpdate();
            return rows>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }


    public int getNbCategory(int depth1, int depth2){
        int nbCategory =0;
        String sql = "SELECT NB_CATEGORY FROM TB_CATEGORY WHERE NB_PARENT_CATEGORY=? AND CN_ORDER=?";
        PreparedStatement pstmt=null;
        ResultSet rs = null;
        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,depth1);
            pstmt.setInt(2, depth2);
            rs = pstmt.executeQuery();
            while(rs.next()){
                nbCategory = rs.getInt(1);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if(rs != null){
                    rs.close();
                    pstmt.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
        return nbCategory;
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
    public String updateFile(String origin , String noProduct) {
        String idFile = "";
        String fileName;
        int flag = 0;
        int rows1 = 0;
        int rows2 = 0;
        int rows3 = 0;
        String fileExt = null;

        System.out.print("파일을 업데이트 하십니까?(Y/N): ");
        String answer = scanner.nextLine();
        PreparedStatement pstmt = null;
        //기존 파일 유지
        if (answer.equalsIgnoreCase("N")) {
            System.out.println("파일을 업데이트 하지 않습니다.");
            return origin;
        }
        // 새로운 파일로 업데이트
        else {
            idFile = UUID.randomUUID().toString().substring(0, 20);

            System.out.print("files 폴더에 존재하는 파일명을 입력하세요.: ");
            fileName = scanner.nextLine();

            // file 존재 여부 확인
            if (!isFileExist(fileName)) {
                return origin;
            }

            //TB_PRODUCT query
            String sql1 = "UPDATE TB_PRODUCT SET "
                    + "ID_FILE=? "
                    + "WHERE NO_PRODUCT= ? ";


            //TB_CONTENT query
            String sql2 = "INSERT INTO TB_CONTENT(ID_FILE, NM_ORG_FILE, BO_SAVE_FILE, NM_FILE_EXT, DA_SAVE, CN_HIT)"
                    + "VALUES (?,?,?,?,SYSDATE,?)";

            String sql3 ="delete from tb_content where id_file=?";

            try {
                //TB_BOARD update -> UPDATE FK
                pstmt = this.connection.prepareStatement(sql1);
                pstmt.setString(1, idFile);
                pstmt.setString(2, noProduct);
                rows1 = pstmt.executeUpdate();


                int idx = fileName.lastIndexOf(".");
                if (idx > 0) {
                    fileExt = fileName.substring(idx + 1);
                    System.out.println("파일 확장자: " + fileExt);
                }

                //TB_CONTENT update -> INSERT
                pstmt = this.connection.prepareStatement(sql2);
                pstmt.setString(1, idFile);
                pstmt.setString(2, fileName);
                pstmt.setBlob(3, new FileInputStream("./files/" + fileName));
                pstmt.setString(4, fileExt);
                pstmt.setInt(5, 0);

                rows2 = pstmt.executeUpdate();
                System.out.println(rows1+" "+rows2);
                if(rows1>0 && rows2>0){
                    System.out.println("파일 삽입 or 업데이트 성공!");
                }

                pstmt = this.connection.prepareStatement(sql3);
                pstmt.setString(1,origin);
                rows3 = pstmt.executeUpdate();

                return idFile;
            } catch (SQLException | FileNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    assert pstmt != null;
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

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

    @Override
    public void readData() {

    }

    @Override
    public boolean insertData() {
        return false;
    }

    @Override
    public boolean updateData(String key) {
        return false;
    }

    @Override
    public boolean deleteData(String[] keys) {
        return false;
    }

    @Override
    public boolean deleteData(String key) {
        return false;
    }
}
