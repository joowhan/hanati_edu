package kr.co.kopo.eCommerce.controller;

import kr.co.kopo.eCommerce.model.TbBasket;
import kr.co.kopo.eCommerce.model.TbBasketItem;
import kr.co.kopo.eCommerce.model.TbOrder;
import kr.co.kopo.eCommerce.model.TbOrderItem;
import kr.co.kopo.product.model.TbProduct;
import kr.co.kopo.user.CurrUser;
import kr.co.kopo.user.model.TbUser;
import kr.co.kopo.util.DbConnection;
import kr.co.kopo.util.Validation;
import oracle.net.ano.SupervisorService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class EcommerceController {
    Connection connection  = null;
    Scanner scanner = null;
    private static LinkedHashMap<String, TbProduct> tbProductList = null;
    private static LinkedHashMap<String, TbUser> tbUserList = null;
    private static LinkedHashMap<Integer, TbBasket> tbBasketList = null;
    private static LinkedHashMap<String, TbOrder> tbOrderList = null;
    private static LinkedHashMap<Integer, TbBasketItem> tbBasketItemList = null;
    public EcommerceController(){
        try{
//            DbConnection.dbUnconnected(connection);
            this.connection = DbConnection.dbConnected();
            scanner = new Scanner(System.in);
            setTbProductList();
            setTbUserList();
            setTbBasketList();

        } catch (SQLException e) {
            System.out.println("DB 연결에 실패하였습니다.");
            throw new RuntimeException(e);
        }
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

    public void setTbUserList(){
        tbUserList = new LinkedHashMap<>();
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        String sql ="SELECT NO_USER, ID_USER, NM_USER, NM_PASWD, NM_EMAIL, ST_STATUS " +
                "FROM TB_USER " +
                "ORDER BY NO_USER ASC";
        try{
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                TbUser tbUser = new TbUser();
                tbUser.setNoUser(rs.getString(1));
                tbUser.setIdUser(rs.getString(2));
                tbUser.setNmUser(rs.getString(3));
                tbUser.setNmPaswd(rs.getString(4));
                tbUser.setNmEmail(rs.getString(5));
                tbUser.setStStatus(rs.getString(6));

                tbUserList.put(tbUser.getNoUser(), tbUser);
            }
        } catch (SQLException e) {
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
    }
    public void setTbBasketList(){
        tbBasketList = new LinkedHashMap<>();
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        String sql ="SELECT NB_BASKET, NO_USER, QT_BASKET_AMOUNT " +
                "FROM TB_BASKET " +
                "ORDER BY NB_BASKET ASC";
        try{
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                TbBasket tbBasket = new TbBasket();
                tbBasket.setNbBasket(rs.getInt(1));
                tbBasket.setNoUser(rs.getString(2));
                tbBasket.setQtBasketAmount(rs.getInt(3));

                tbBasketList.put(tbBasket.getNbBasket(), tbBasket);
            }
        } catch (SQLException e) {
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
    }
    public void setTbBasketItemList(){
        tbBasketItemList = new LinkedHashMap<>();
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        String sql="SELECT NB_BASKET_ITEM, NB_BASKET, CN_BASKET_ITEM_ORDER, NO_PRODUCT," +
                " NO_USER, QT_BASKET_ITEM_PRICE, QT_BASKET_ITEM,QT_BASKET_ITEM_AMOUNT " +
                "FROM TB_BASKET_ITEM " +
                "WHERE NO_USER = ?";
        try{

            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,CurrUser.getCurrUser().getNoUser());
            rs = pstmt.executeQuery();
            while(rs.next()){
                TbBasketItem tbBasketItem = new TbBasketItem();
                tbBasketItem.setNbBasketItem(rs.getInt(1));
                tbBasketItem.setNbBasket(rs.getInt(2));
                tbBasketItem.setCnBasketItemOrder(rs.getInt(3));
                tbBasketItem.setNoProduct(rs.getString(4));
                tbBasketItem.setNoUser(rs.getString(5));
                tbBasketItem.setQtBasketItemPrice(rs.getInt(6));
                tbBasketItem.setQtBasketItem(rs.getInt(7));
                tbBasketItem.setQtBasketItemAmount(rs.getInt(8));
                tbBasketItemList.put(tbBasketItem.getNbBasketItem(), tbBasketItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTbOrderList(){

    }
    public LinkedHashMap<String, TbProduct> getTbProductList(){
        return tbProductList;
    }

    //상품 목록 조회
    public void readData(int nbCategory){
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        int inputData = 0;

        String sql ="SELECT P.NO_PRODUCT, P.NM_PRODUCT, C.NM_ORG_FILE, P.QT_SALE_PRICE, P.QT_DELIVERY_FEE, M.NB_CATEGORY " +
                "FROM TB_PRODUCT P LEFT OUTER JOIN TB_CONTENT C ON P.ID_FILE = C.ID_FILE JOIN tb_category_product_mapping M ON M.NO_PRODUCT = P.NO_PRODUCT " +
                "WHERE M.NB_CATEGORY = ?"+
                "ORDER BY P.NO_PRODUCT DESC";

        try{
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

                System.out.println("------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if(rs!=null){
                    rs.close();
                    pstmt.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }

    //상품 주문
    public boolean orderProduct(String noProduct, int qtStock,int cnt){
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        int rows1 = 0;
        int rows2 = 0;
        //상품이 존재하고 재고가 남아 있는지? -> tbProductList를 이용했을 때
        String sql1="select qt_stock " +
                "from tb_product " +
                "where no_product = ?";
        try{
            pstmt = connection.prepareStatement(sql1);
            pstmt.setString(1, noProduct);
            rs = pstmt.executeQuery();

            if(rs.next()){
                if(rs.getInt(1)<qtStock){
                    System.out.println("재고 수량이 부족합니다.");
                    return false;
                }
            }
            else{
                System.out.println("해당 상품은 존재하지 않습니다.");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // 해당 주문이 이미 테이블에 존재하는지? -> 존재한다면 해당 주문 업데이트 -> 단일 주문에 대해서는 1주문 - 1아이템, 단체 주문 1주문-여러 아이템
        //주문 테이블에 주문 기록 부여 insert -> tbProductList에서도 반영 -> 수량
        TbOrder tbOrder = new TbOrder();
        String sql2 = "select 'OR' || LPAD(seq_tb_order.nextval, 7, '0') from dual";
        String sql3 = "INSERT INTO TB_ORDER(ID_ORDER, NO_USER, QT_ORDER_AMOUNT, QT_DELI_MONEY,QT_DELI_PERIOD, NM_ORDER_PERSON, DA_ORDER, ST_ORDER) " +
                " VALUES(?,?,?,?,?,?,SYSDATE,'10')";

        try{
            pstmt = connection.prepareStatement(sql2);
            rs = pstmt.executeQuery();
            if(rs.next()){
                tbOrder.setIdOrder(rs.getString(1));
            }
            else{
                return false;
            }

            tbOrder.setNoUser(CurrUser.getCurrUser().getNoUser());
            tbOrder.setQtOrderAmount(tbProductList.get(noProduct).getQtCustomer()*qtStock);
            tbOrder.setQtDeilMoney(tbProductList.get(noProduct).getQtDeliveryFee());
            tbOrder.setQtDeilPeriod(3);
            tbOrder.setNmOrderPerson(CurrUser.getCurrUser().getNmUser());

            pstmt = connection.prepareStatement(sql3);
            pstmt.setString(1, tbOrder.getIdOrder());
            pstmt.setString(2, tbOrder.getNoUser());
            pstmt.setInt(3, tbOrder.getQtOrderAmount());
            pstmt.setInt(4, tbOrder.getQtDeilMoney());
            pstmt.setInt(5, tbOrder.getQtDeilPeriod());
            pstmt.setString(6, tbOrder.getNmOrderPerson());

            rows1 = pstmt.executeUpdate();


            if(rows1 <= 0){
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //주문 아이템 테이블에 구매한 상품 넣기 insert
        TbOrderItem tbOrderItem = new TbOrderItem();
        String sql4 = "select 'OI' || LPAD(seq_tb_order_item.nextval, 7, '0') from dual";
        String sql5 = "INSERT INTO TB_ORDER_ITEM(ID_ORDER_ITEM, ID_ORDER, CN_ORDER_ITEM, NO_PRODUCT, NO_USER, QT_UNIT_PRICE, QT_ORDER_ITEM, QT_ORDER_ITEM_AMOUNT, QT_ORDER_ITEM_DELIVERY_FEE) " +
                " VALUES (?,?,?,?,?,?,?,?,?)";
        try{
            pstmt = connection.prepareStatement(sql4);
            rs = pstmt.executeQuery();
            if(rs.next()){
                tbOrderItem.setIdOrderItem(rs.getString(1));
            }
            else{
                return false;
            }

            tbOrderItem.setIdOrder(tbOrder.getIdOrder());
            tbOrderItem.setCnOrderItem(cnt);
            tbOrderItem.setNoProduct(noProduct);
            tbOrderItem.setNoUser(CurrUser.getCurrUser().getNoUser());
            tbOrderItem.setQtUnitPrice(tbProductList.get(noProduct).getQtCustomer());
            tbOrderItem.setQtOrderItem(qtStock);
            tbOrderItem.setQtOrderItemAmount(tbOrder.getQtOrderAmount());
            tbOrderItem.setQtOrderItemDeliveryFee(tbProductList.get(noProduct).getQtDeliveryFee());

            pstmt = connection.prepareStatement(sql5);
            pstmt.setString(1,tbOrderItem.getIdOrderItem());
            pstmt.setString(2, tbOrderItem.getIdOrder());
            pstmt.setInt(3,tbOrderItem.getCnOrderItem());
            pstmt.setString(4, tbOrderItem.getNoProduct());
            pstmt.setString(5,tbOrderItem.getNoUser());
            pstmt.setInt(6,tbOrderItem.getQtUnitPrice());
            pstmt.setInt(7,tbOrderItem.getQtOrderItem());
            pstmt.setInt(8,tbOrderItem.getQtOrderItemAmount());
            pstmt.setInt(9,tbOrderItem.getQtOrderItemDeliveryFee());

            rows2 = pstmt.executeUpdate();

            //재고 줄이기
            String sql6 = "UPDATE TB_PRODUCT SET QT_STOCK = QT_STOCK-1 WHERE NO_PRODUCT = ?";
            pstmt = connection.prepareStatement(sql6);
            pstmt.setString(1, noProduct);
            pstmt.executeUpdate();

            if(rows2<=0) return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(rs!=null && pstmt!=null){
                    pstmt.close();
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return true;
    }

    //장바구니 담기
    public boolean insertItemToBasket(String noProduct, int qtStock){
        PreparedStatement pstmt=null;
        ResultSet rs = null;
        int rows = 0;
        TbBasket tbBasket = CurrUser.getCurrUserBasket();
        TbBasketItem tbBasketItem = new TbBasketItem();
        //tb_basket의 금액 update, db에도 반영
        String sql = "UPDATE TB_BASKET SET QT_BASKET_AMOUNT = ? WHERE NB_BASKET = ?";
        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, tbBasket.getQtBasketAmount()+tbProductList.get(noProduct).getQtSalePrice());
            pstmt.setInt(2, tbBasket.getNbBasket());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tbBasket.setQtBasketAmount(tbBasket.getNbBasket()+tbProductList.get(noProduct).getQtSalePrice());
        //tb_basket_item 테이블에 insert
        String sql1 = "select seq_tb_basket_item.nextval from dual";
        String sql2 = "SELECT COUNT(*) FROM TB_BASKET_ITEM WHERE NB_BASKET=?";
        String sql3 = "INSERT INTO TB_BASKET_ITEM(NB_BASKET_ITEM, NB_BASKET, CN_BASKET_ITEM_ORDER, NO_PRODUCT, NO_USER, QT_BASKET_ITEM_PRICE, QT_BASKET_ITEM,QT_BASKET_ITEM_AMOUNT) " +
                "VALUES(?,?,?,?,?,?,?,?)";

        try{
            pstmt = connection.prepareStatement(sql1);
            rs = pstmt.executeQuery();
            if(rs.next()){
                tbBasketItem.setNbBasketItem(rs.getInt(1));
            }

            pstmt = connection.prepareStatement(sql2);
            pstmt.setInt(1, tbBasket.getNbBasket());
            rs = pstmt.executeQuery();
            if(rs.next()){
                tbBasketItem.setCnBasketItemOrder(rs.getInt(1)+1); //품목 순번 => count(item)+1
            }
            //table set
            tbBasketItem.setNbBasket(tbBasket.getNbBasket());
            tbBasketItem.setNoProduct(noProduct);
            tbBasketItem.setNoUser(CurrUser.getCurrUser().getNoUser());
            tbBasketItem.setQtBasketItemPrice(tbProductList.get(noProduct).getQtSalePrice());
            tbBasketItem.setQtBasketItem(qtStock);
            tbBasketItem.setQtBasketItemAmount(tbProductList.get(noProduct).getQtSalePrice()*qtStock);

            pstmt = connection.prepareStatement(sql3);
            pstmt.setInt(1,tbBasketItem.getNbBasketItem());
            pstmt.setInt(2,tbBasketItem.getNbBasket());
            pstmt.setInt(3,tbBasketItem.getCnBasketItemOrder());
            pstmt.setString(4,tbBasketItem.getNoProduct());
            pstmt.setString(5,tbBasketItem.getNoUser());
            pstmt.setInt(6,tbBasketItem.getQtBasketItemPrice());
            pstmt.setInt(7,tbBasketItem.getQtBasketItem());
            pstmt.setInt(8,tbBasketItem.getQtBasketItemAmount());

            rows = pstmt.executeUpdate();
            return rows>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(rs!=null && pstmt!=null){
                    pstmt.close();
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void readBasket(){
        ResultSet rs =null;
        PreparedStatement pstmt = null;
        String sql ="SELECT I.CN_BASKET_ITEM_ORDER, I.NO_PRODUCT, B.NM_PRODUCT," +
                "I.QT_BASKET_ITEM, I.QT_BASKET_ITEM_AMOUNT " +
                "FROM TB_BASKET_ITEM I JOIN TB_PRODUCT B ON I.NO_PRODUCT=B.NO_PRODUCT " +
                "WHERE I.NB_BASKET = ? ";
        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, CurrUser.getCurrUserBasket().getNbBasket());
            rs = pstmt.executeQuery();
            System.out.printf("%-15s %-18s %-20s %-20s %-20s \n","순번","상품코드","상품명","수량","금액");
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            while(rs.next()){
                System.out.printf("%-15s %-18s %-20s %-20s %-20s \n",
                        rs.getInt(1),rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getInt(5));
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(rs!=null && pstmt!=null){
                    pstmt.close();
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    // 장바구니 전체 조회
    public boolean orderBasketList(){
        //주문 nextval 생성
        String sql1 = "select 'OR' || LPAD(seq_tb_order.nextval, 7, '0') from dual";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String idOrder = null;
        String sql2 = "DELETE FROM TB_BASKET_ITEM WHERE NO_USER =?";
        try{
            pstmt = connection.prepareStatement(sql1);
            rs = pstmt.executeQuery();
            if(rs.next())
                idOrder = rs.getString(1);
            int cnt = 0;
            for(Map.Entry<Integer,TbBasketItem> entry:tbBasketItemList.entrySet()){
                cnt ++;
                orderBasketItem(idOrder, entry.getValue(), cnt);
            }

            //장바구니 내역 전부 삭제 tbBasketItemList = null;
            tbBasketItemList = null;
            pstmt = connection.prepareStatement(sql2);
            pstmt.setString(1, CurrUser.getCurrUser().getNoUser());
            int rows = pstmt.executeUpdate();
            return rows>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void orderBasketItem(String idOrder, TbBasketItem tbBasketItem, int cnt){
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        TbOrder tbOrder = new TbOrder();
        //idOrder가 TB_ORDER에 존재하지 않는다면 -> TB_ORDER에 idOrder로 새롭게 추가
        try{
            String sql1 = "select id_order " +
                    "from tb_order " +
                    "where id_order = ?";
            String sql2 ="INSERT INTO TB_ORDER(ID_ORDER, NO_USER, QT_ORDER_AMOUNT, QT_DELI_MONEY,QT_DELI_PERIOD, NM_ORDER_PERSON, DA_ORDER, ST_ORDER) " +
                    " VALUES(?,?,?,?,?,?,SYSDATE,'10')";
            pstmt = connection.prepareStatement(sql1);
            pstmt.setString(1,idOrder);
            rs = pstmt.executeQuery();
            // 존재하지 않는다면 TB_ORDER 추가
            if(!rs.next()){
                pstmt = connection.prepareStatement(sql2);


                tbOrder.setIdOrder(idOrder);
                tbOrder.setNoUser(CurrUser.getCurrUser().getNoUser());
                tbOrder.setQtOrderAmount(tbProductList.get(tbBasketItem.getNoProduct()).getQtCustomer()*tbBasketItem.getQtBasketItem());
                tbOrder.setQtDeilMoney(tbProductList.get(tbBasketItem.getNoProduct()).getQtDeliveryFee());
                tbOrder.setQtDeilPeriod(3);
                tbOrder.setNmOrderPerson(CurrUser.getCurrUser().getNmUser());


                pstmt.setString(1, tbOrder.getIdOrder());
                pstmt.setString(2, tbOrder.getNoUser());
                pstmt.setInt(3, tbOrder.getQtOrderAmount());
                pstmt.setInt(4, tbOrder.getQtDeilMoney());
                pstmt.setInt(5, tbOrder.getQtDeilPeriod());
                pstmt.setString(6, tbOrder.getNmOrderPerson());
                int rows =pstmt.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //재고가 부족한지 확인
        String sql3="select qt_stock " +
                "from tb_product " +
                "where no_product = ?";
        try{
            pstmt = connection.prepareStatement(sql3);
            pstmt.setString(1, tbBasketItem.getNoProduct());
            rs = pstmt.executeQuery();

            if(rs.next()){
                if(rs.getInt(1) < tbBasketItem.getQtBasketItem()){
                    System.out.println("재고 수량이 부족합니다.");
                    return;
                }
            }
            else{
                System.out.println("해당 상품은 존재하지 않습니다.");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //주문에 해당하는 orderItem에도 추가 + TB_ORDER 금액 업데이트
        TbOrderItem tbOrderItem = new TbOrderItem();
        String sql4 = "select 'OI' || LPAD(seq_tb_order_item.nextval, 7, '0') from dual";
        String sql5 = "INSERT INTO TB_ORDER_ITEM(ID_ORDER_ITEM, ID_ORDER, CN_ORDER_ITEM, NO_PRODUCT, NO_USER, QT_UNIT_PRICE, QT_ORDER_ITEM, QT_ORDER_ITEM_AMOUNT, QT_ORDER_ITEM_DELIVERY_FEE) " +
                " VALUES (?,?,?,?,?,?,?,?,?)";
        try{
            pstmt = connection.prepareStatement(sql4);
            rs = pstmt.executeQuery();
            if(rs.next()){
                tbOrderItem.setIdOrderItem(rs.getString(1));
            }
            else{
                return;
            }
            String noProduct = tbBasketItem.getNoProduct();
            tbOrderItem.setIdOrder(idOrder);
            tbOrderItem.setCnOrderItem(cnt);
            tbOrderItem.setNoProduct(noProduct);
            tbOrderItem.setNoUser(CurrUser.getCurrUser().getNoUser());
            tbOrderItem.setQtUnitPrice(tbBasketItem.getQtBasketItemPrice());
            tbOrderItem.setQtOrderItem(tbBasketItem.getQtBasketItem());
            tbOrderItem.setQtOrderItemAmount(tbBasketItem.getQtBasketItemAmount());
            tbOrderItem.setQtOrderItemDeliveryFee(tbProductList.get(noProduct).getQtDeliveryFee());

            pstmt = connection.prepareStatement(sql5);
            pstmt.setString(1,tbOrderItem.getIdOrderItem());
            pstmt.setString(2, tbOrderItem.getIdOrder());
            pstmt.setInt(3,tbOrderItem.getCnOrderItem());
            pstmt.setString(4, tbOrderItem.getNoProduct());
            pstmt.setString(5,tbOrderItem.getNoUser());
            pstmt.setInt(6,tbOrderItem.getQtUnitPrice());
            pstmt.setInt(7,tbOrderItem.getQtOrderItem());
            pstmt.setInt(8,tbOrderItem.getQtOrderItemAmount());
            pstmt.setInt(9,tbOrderItem.getQtOrderItemDeliveryFee());

            int rows2 = pstmt.executeUpdate();
            //재고 줄이기
            String sql6 = "UPDATE TB_PRODUCT SET QT_STOCK = QT_STOCK-1 WHERE NO_PRODUCT = ?";
            pstmt = connection.prepareStatement(sql6);
            pstmt.setString(1, noProduct);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(rs!=null && pstmt!=null){
                    pstmt.close();
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    //회원가입
    public boolean signUp() {
        String sql1="INSERT INTO TB_USER(NO_USER, ID_USER, NM_USER, NM_PASWD, NM_EMAIL, ST_STATUS) " +
                "VALUES(?,?,?,?,?,'ST01')";
        String sql2="SELECT 'PD' || LPAD(seq_tb_user.nextval, 5, '0') FROM DUAL";
        String sql3="insert into tb_basket(nb_basket, no_user, qt_basket_amount) "+
                "values(?, ?,0)";
        String sql4="SELECT seq_tb_user.CURRVAL FROM DUAL";

        PreparedStatement pstmt=null;
        ResultSet rs = null;
        int rows1 = 0;
        int rows2 = 0;

        // 새롭게 id, pwd 생성
        TbUser tbUser = new TbUser();
        TbBasket tbBasket = new TbBasket();
//        System.out.println("뒤로가기: exit 입력");
        System.out.print("이름을 입력하세요: ");
        tbUser.setNmUser(scanner.nextLine());


        while(true){
            System.out.print("ID를 입력하세요: ");
            tbUser.setIdUser(scanner.nextLine());
            if(isUserExist(tbUser.getIdUser())){
                System.out.println("이미 아이디가 존재합니다.");
            }
            //적합하다면
            else if(Validation.idValidation(tbUser.getIdUser())){
                break;
            }
            System.out.println("아이디는 영문자, 숫자 사용 가능, 5~15자리여야 합니다.");
        }

        while(true){
            System.out.print("비밀번호를 입력하세요: ");
            tbUser.setNmPaswd(scanner.nextLine());
            //적합하다면
            if(Validation.pwdValidation(tbUser.getNmPaswd())){
                break;
            }
            System.out.println("비밀번호는 영문자(대문자/소문자 1 이상 포함), 숫자 1이상 포함, 5~15자리여야 합니다.");
        }

        while(true){
            System.out.print("이메일을 입력하세요: ");
            tbUser.setNmEmail(scanner.nextLine());
            //적합하다면
            if(Validation.emailValidation(tbUser.getNmEmail())){
                break;
            }
            System.out.println("이메일 형식을 확인하세요.");
        }

        try{
            pstmt = this.connection.prepareStatement(sql2);
            rs = pstmt.executeQuery();
            while(rs.next())
                tbUser.setNoUser(rs.getString(1));

            pstmt = this.connection.prepareStatement(sql1);
            pstmt.setString(1,tbUser.getNoUser());
            pstmt.setString(2,tbUser.getIdUser());
            pstmt.setString(3, tbUser.getNmUser());
            pstmt.setString(4, tbUser.getNmPaswd());
            pstmt.setString(5, tbUser.getNmEmail());
            rows1 = pstmt.executeUpdate();


            //유저의 장바구니 생성
            pstmt = this.connection.prepareStatement(sql4);
            rs = pstmt.executeQuery();
            while(rs.next())
                tbBasket.setNbBasket(rs.getInt(1));

            pstmt = this.connection.prepareStatement(sql3);
            pstmt.setInt(1, tbBasket.getNbBasket());
            pstmt.setString(2,tbUser.getNoUser());
            rows2 = pstmt.executeUpdate();
            if(rows1>0 && rows2>0){
                tbUserList.put(tbUser.getNoUser(),tbUser);
                rs.close();
                return true;

            }
            return false;

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
    //로그인
    public void loginController(){
        ResultSet rs = null;
        PreparedStatement pstmt=null;

        //로그인 되어 있다면
        if(Validation.isLogined()){
            CurrUser.setCurrUser(null);
            CurrUser.setCurrUserBasket(null);
        }
        //로그인 해야 한다면
        else{
            //아이디 입력받고 디비에서 id 불러오기
            System.out.print("아이디 입력: ");
            String id = scanner.nextLine();
            String sql ="select u.no_user, b.nb_basket " +
                    "from tb_user u  join tb_basket b on u.no_user = b.no_user " +
                    "where u.id_user = ? ";
            String noUser="";
            int nbBasket=0;
            try{
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1,id);
                rs = pstmt.executeQuery();
                while(rs.next()){
                    noUser = rs.getString(1);
                    nbBasket = rs.getInt(2);
                }

                if(tbUserList.containsKey(noUser) && Validation.login(tbUserList.get(noUser))){
                    CurrUser.setCurrUser(tbUserList.get(noUser));
                    CurrUser.setCurrUserBasket(tbBasketList.get(nbBasket));
                    System.out.println("로그인 성공!");
                }
                else{
                    System.out.println("아이디, 비밀번호를 확인하세요. 로그인 실패!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
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
}
