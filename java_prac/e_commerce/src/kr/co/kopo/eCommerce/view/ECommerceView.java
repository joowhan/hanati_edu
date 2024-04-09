package kr.co.kopo.eCommerce.view;

import kr.co.kopo.product.ProductCrud;
import kr.co.kopo.product.model.TbProduct;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class ECommerceView {
    public void printMainView(){
        ProductCrud productCrud = new ProductCrud();
        LinkedHashMap<String, TbProduct> tbProductList = productCrud.getTbProductList();
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
                    this.printProductsDetailView(nbCategory);
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
                    this.printProductsDetailView(nbCategory);
                }
            }
            else if(depth1==3){
                break;
            }
            else{
                System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    public void printProductsDetailView(int nbCategory){

    }
}
