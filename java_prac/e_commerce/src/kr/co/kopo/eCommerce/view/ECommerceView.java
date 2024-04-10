package kr.co.kopo.eCommerce.view;

import kr.co.kopo.eCommerce.controller.EcommerceController;
import kr.co.kopo.product.model.TbProduct;
import kr.co.kopo.user.CurrUser;
import kr.co.kopo.util.Validation;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class ECommerceView {

    Scanner scanner = new Scanner(System.in);
    private EcommerceController ecommerceController =null;
    public void printMainView(){
//        ProductCrud productCrud = new ProductCrud();
//        LinkedHashMap<String, TbProduct> tbProductList = productCrud.getTbProductList();
        this.ecommerceController = new EcommerceController();
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
                nbCategory = ecommerceController.getNbCategory(depth1,depth2);
                if(nbCategory <=0){
                    System.out.println("잘못 입력하셨습니다. ");
                    break;
                }
                else {
                    this.printProductsDetailView(nbCategory, ecommerceController);
                }
            }
            else if(depth1==2){
                System.out.println("조회하고 싶은 하위 카테고리를 선택하세요.");
                System.out.println("1.수납/정리용품 2.청소 용품 3.구강위생용품 ");
                System.out.print("선택 > ");
                depth2 =  scanner.nextInt();
                scanner.nextLine();
                nbCategory = ecommerceController.getNbCategory(depth1,depth2);
                if(nbCategory <=0){
                    System.out.println("잘못 입력하셨습니다. ");
                    break;
                }
                else {
                    this.printProductsDetailView(nbCategory, ecommerceController);
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
    //상품 목록 출력
    public void printProductsDetailView(int nbCategory, EcommerceController ecommerceController){
        int inputData = 0;
        String noProduct;
        String loginedMsg;
        while(true){
            LinkedHashMap<String, TbProduct> tbProductList = ecommerceController.getTbProductList();
//            for(Map.Entry<String, TbProduct> entry : tbProductList.entrySet()){
//                System.out.printf();
//            }
            ecommerceController.readData(nbCategory);
            if(!Validation.isLogined()){
                System.out.println("접속 계정: 계정 없음");
                loginedMsg = "로그인";
            }
            else{
                System.out.println("접속 계정: "+ CurrUser.getCurrUser().getIdUser());
                loginedMsg ="로그아웃";
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            System.out.println("1.상품 장바구니 담기\t 2.주문\t 3.장바구니 조회\t 4."+loginedMsg+"\t 5.회원가입\t 6.뒤로가기");
            System.out.print("선택 > ");
            inputData = scanner.nextInt();
            scanner.nextLine();

            try{
                //상품 장바구니 담기
                if(inputData==1){
                    this.printBusketView(ecommerceController);
                }
                //주문
                else if(inputData==2){
                    this.printOrderView(ecommerceController);
                }
                //장바구니 조회 -> 주문
                else if(inputData==3){

                }
                //로그인
                else if (inputData==4) {
                    ecommerceController.loginController();
                }
                //회원가입
                else if(inputData==5){
                    if(ecommerceController.signUp())
                        System.out.println("회원가입 성공!");
                    else
                        System.out.println("회원가입 실패!");
                }
                else if(inputData==6){
                    System.out.println("뒤로가기");
                    break;
                }
                else{
                    System.out.println("다시 입력하세요.");
                }
            }catch(InputMismatchException e){
                System.out.println("정수만 입력 가능합니다. ");
                scanner.nextLine();
                break;
            }
        }
    }
    public void printBusketView(EcommerceController ecommerceController){
        if(!Validation.isLogined()){
            System.out.println("로그인 후 이용해주세요.");
            return;
        }
        System.out.print("장바구니에 담을 상품 ID를 입력하세요!: ");
        String noProduct = scanner.nextLine();
        System.out.print("구매할 개수를 입력하세요: ");
        int qtStock = scanner.nextInt();
        scanner.nextLine();
        ecommerceController.insertItemToBusket(noProduct,qtStock);


    }
    public void printBusketDetailView(EcommerceController ecommerceController){
        if(!Validation.isLogined()){
            System.out.println("로그인 후 이용해주세요.");
            return;
        }
    }
    public void printOrderView(EcommerceController ecommerceController){
        System.out.print("주문할 상품 코드를 입력하세요: ");
        String noProduct = scanner.nextLine();
        System.out.print("구매할 개수를 입력하세요: ");
        int qtStock = scanner.nextInt();
        scanner.nextLine();

        if(!Validation.isLogined()){
            System.out.println("로그인 후 이용해주세요.");
            return;
        }
        if(ecommerceController.orderProduct(noProduct, qtStock,1)){
            System.out.println("주문 성공!");
        }
        else{
            System.out.println("주문 실패!");
        }
    }

}
