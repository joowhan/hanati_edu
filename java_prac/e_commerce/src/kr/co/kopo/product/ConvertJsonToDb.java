package kr.co.kopo.product;

import kr.co.kopo.product.model.TbProduct;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ConvertJsonToDb {
    private static LinkedHashMap<Integer, TbProduct> productList=null;
    ConvertJsonToDb() throws IOException {
        if(productList == null){
            File file = new File("./shoppingMall.json");
            //이미 파일이 존재한다면 바로 데이터 읽기
            if(file.exists()){
                readDataJson();
            }
            //파일이 존재하지 않는다면 새로 생성
            else{
                productList = new LinkedHashMap<>();
                if(file.createNewFile()){
                    System.out.println("새롭게 파일을 생성합니다!");
                }
                else{
                    System.out.println("파일 생성 실패!");
                }
            }

        }
    }
    private void readDataJson() throws IOException {
        int idx = 0;

        productList = new LinkedHashMap<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("./shoppingMall.json")) {
            // JSON 파일을 파싱하여 JSON 객체로 읽어옵니다.
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            // JSON 객체의 모든 키를 가져옵니다.
            Set<String> keys = jsonObject.keySet();

            // 각 키에 대해 반복하여 해당 배열을 가져와서 데이터를 읽고 처리합니다.
            for (String key : keys) {
                JSONArray jsonArray = (JSONArray) jsonObject.get(key);
                System.out.println("키: " + key);

                for (Object arrayObj : jsonArray) {
                    JSONObject item = (JSONObject) arrayObj;
                    String productName = (String) item.get("nm_product");
                    String detailExplain = (String) item.get("nm_detail_explain");
                    String salePrice = (String) item.get("qt_sale_price");
                    String deliveryFee = (String) item.get("qt_delevery_fee");

                    System.out.println("제품명: " + productName);
                    System.out.println("상세 설명: " + detailExplain);
                    System.out.println("판매 가격: " + salePrice);
                    System.out.println("배송 비용: " + deliveryFee);
                    System.out.println();
                }
            }
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

    }
}
