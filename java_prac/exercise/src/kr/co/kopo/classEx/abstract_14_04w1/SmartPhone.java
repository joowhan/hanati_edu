package kr.co.kopo.classEx.abstract_14_04w1;

public class SmartPhone extends Phone{

    SmartPhone(String owner) {
        super(owner);
    }

    void internetSearch(){
        System.out.println("인터넷을 검색합니다.");
    }
}
