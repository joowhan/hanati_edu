import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConvertJsonToDb {
    private static LinkedHashMap<Integer, TbBoard> boardList=null;
    ConvertJsonToDb() throws IOException {
        if(boardList == null){
            File file = new File("economicNews.json");
            //이미 파일이 존재한다면 바로 데이터 읽기
            if(file.exists()){
                readDataJson();
            }
            //파일이 존재하지 않는다면 새로 생성
            else{
                boardList = new LinkedHashMap<>();
                if(file.createNewFile()){
                    System.out.println("새롭게 파일을 생성합니다!");
                }
                else{
                    System.out.println("파일 생성 실패!");
                }
            }

        }
    }
    public LinkedHashMap<Integer, TbBoard> getBoardList(){
        return boardList;
    }

    //data를 읽어와서 json으로 저장
    private void readDataJson() throws IOException {
        int idx = 0;

        boardList = new LinkedHashMap<>();
        JSONParser jsonParser = new JSONParser();
            try(FileReader reader = new FileReader("economicNews.json")){
                System.out.println("데이터 읽기 성공!");
                Object object = jsonParser.parse(reader);
                JSONArray jsonArray = (JSONArray) object;
                for(Object object1: jsonArray){
                    JSONObject jsonObject = (JSONObject) object1;
                    String date = (String) jsonObject.get("date");
                    String title = (String) jsonObject.get("title");
                    String content = (String) jsonObject.get("content");
                    String writer ="김주환";
                    int hit = 0;
                    content = content.substring(0,100);
                    if(content.length()>3500){
                        continue;
                    }
                    idx ++;
                    boardList.put(idx, new TbBoard(date, title, content.substring(1).trim(), writer,hit));
                }
            }catch (IOException | ParseException e) {
                e.printStackTrace();
            }

    }
    //board를 데이터베이스에 저장
    public boolean storedToDb(){
        int rows = 0;
        PreparedStatement pstmt = null;
        Connection connection = null;
        try{
            connection = DbConnection.dbConnected();

            String sql = "INSERT INTO TB_BOARD(NB_BOARD, NM_TITLE, NM_CONTENT,NM_WRITER, DA_WRITER, CN_HIT)\n" +
                    "VALUES(seq_tb_board.nextval, ?,?,?,SYSDATE,?)";
//            PreparedStatement pstmt = connection.prepareStatement(sql);
            for(Map.Entry<Integer,TbBoard> entry: boardList.entrySet()){

                pstmt = connection.prepareStatement(sql);
                TbBoard post = boardList.get(entry.getKey());
                String nmContent = post.getNmContent();
                if(nmContent.length()>4000){
                    nmContent = nmContent.substring(0,3000);
                    System.out.println(nmContent.length());
                }
                pstmt.setString(1,post.getNmTitle());
                pstmt.setString(2,nmContent);
                pstmt.setString(3, post.getNmWriter());
                pstmt.setInt(4, post.getCnHit());

                rows += pstmt.executeUpdate();


            }
            System.out.println(rows+" row insert 완료!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                assert pstmt != null;
                pstmt.close();
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
        return true;

    }

    public static void main(String[] args) throws IOException {
        ConvertJsonToDb convertJsonToDb = new ConvertJsonToDb();
//        LinkedHashMap<Integer,TbBoard> boardList = convertCsvToDb.getBoardList();
        convertJsonToDb.storedToDb();


    }
}

