import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NewsCrawling{
    public static void main(String[] args) {

        // 크롤링 할 사이트 주소.
        String url = "https://sports.news.naver.com/wfootball/index.nhn";

        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            System.out.println(e);
        }

        // 1. 주요 뉴스로 나오는 태그 찾아서 가져옴.
        Elements element = doc.select("div.home_news");

        // 2. 헤더 제목 가져오기.
        String title = element.select("h2").text().substring(0, 4);

        System.out.println("====================================");
        System.out.println("헤더 제목 : " + title);
        System.out.println("====================================");

        // 3. for문 이용 하위 뉴스 기사들 출력
        for (Element el : element.select("li")) {
            System.out.println(el.text());
        }

        System.out.println("====================================");

//        try {
//            //아래 URL 은 삭제되었을 수 있으므로, 문제가 발생한다면 최신 기사의 URL 을 사용한다.
//            Document doc1 = Jsoup.connect("https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=421&aid=0004721371").get();
//
//            Elements elements = doc1.select("#articleBodyContents");
//
//            String[] p = elements.get(0).text().split("\\.");
//
//            for (int i = 0; i < p.length; i++) {
//                System.out.println(p[i]);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            //1. url 접근
            doc = Jsoup.connect("http://www.google.com").get();
            System.out.println(doc.text()); //text() : 웹 페이지 내의 텍스트만 추출
            System.out.println(doc.select(".Fx4vi").first()); //select(CSS Selector) : 웹 페이지에서 원하는 태그 검색
            System.out.println(doc.select(".Fx4vi").last());

            //2. 내가 추출하고자 하는 데이터 검색
            Elements elements = doc.select(".Fx4vi"); //Elements : ArrayList<Element> 형태로 태그가 저장
            System.out.println(elements.get(0).text());
            System.out.println(elements.get(3).text());
            System.out.println(elements.get(0).attr("href")); //attr(Attrubute) : 해당 태그의 속성값 추출

            System.out.println();
            for (int i = 0; i < elements.size(); i++) {
                System.out.println(elements.get(i).text() + " : " + elements.get(i).attr("href"));
                //클래스명이 `.Fx4vi` 인 태그에 해당하는 Text와 링크주소 출력
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}