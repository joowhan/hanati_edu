package kr.co.kopo.product;

import kr.co.kopo.util.DbConnection;

import java.util.Scanner;

public class ProductEx {
    public static void main(String[] args) {
        ProductCrud productCrud = new ProductCrud();
        int depth1 = 0;
        int depth2 = 0;
        int nbCategory = 0;
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("조회하고 싶은 카테고리를 선택하세요.");
            System.out.println("1. 가전 2. 생활/주방용품 3.종료");
            System.out.print("선택 > ");
            depth1 = scanner.nextInt();
            scanner.nextLine();


            if(depth1==1){
                System.out.println("조회하고 싶은 하위 카테고리를 선택하세요.");
                System.out.println("1.tv 2.청소기 3.냉장고 ");
                System.out.print("선택 > ");
                depth2 =  scanner.nextInt();
                scanner.nextLine();
                nbCategory = productCrud.getNbCategory(depth1,depth2);
                if(nbCategory <=0){
                    System.out.println("잘못 입력하셨습니다. ");
                    break;
                }
                else {
                    productCrud.readData(nbCategory);
                }
            }
            else if(depth1==2){
                System.out.println("조회하고 싶은 하위 카테고리를 선택하세요.");
                System.out.println("1.수납/정리용품 2.청소 용품 3.구강위생용품 ");
                System.out.print("선택 > ");
                depth2 =  scanner.nextInt();
                scanner.nextLine();
                nbCategory = productCrud.getNbCategory(depth1,depth2);
                if(nbCategory <=0){
                    System.out.println("잘못 입력하셨습니다. ");
                    break;
                }
                else {
                    productCrud.readData(nbCategory);
                }
            }
            else if(depth1==3){
                break;
            }
            else{
                System.out.println("잘못 입력하셨습니다.");
            }
//            System.out.print("선택 > ");
//            depth2 =  scanner.nextInt();
//            scanner.nextLine();
//            nbCategory = productCrud.getNbCategory(depth1,depth2);
//            if(nbCategory <=0){
//                System.out.println("잘못 입력하셨습니다. ");
//                break;
//            }
//            else {
//                productCrud.readData(nbCategory);
//            }

        }
        System.out.println("프로그램 종료");
        DbConnection.dbUnconnected(productCrud.connection);

    }
}
