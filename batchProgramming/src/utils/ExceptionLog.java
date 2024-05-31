package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExceptionLog {
    public static void writeLog(Exception exception, String className){
        try{
            File logFile = new File("./exception.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile, true));
            bufferedWriter.newLine();
            bufferedWriter.write("*******************************************************************************************************************");

            // 발생한 클래스 이름 추가
            bufferedWriter.newLine();
            bufferedWriter.write("Class: " + className);

            // 타임스탬프 추가
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            bufferedWriter.newLine();
            bufferedWriter.write("Timestamp: " + timeStamp);

            // 예외 유형 추가
            bufferedWriter.newLine();
            bufferedWriter.write("Exception Type: " + exception.getClass().getName());

            // 예외 메시지 추가
            bufferedWriter.newLine();
            bufferedWriter.write("Message: " + exception.getMessage());

            // 스택 트레이스 추가
            bufferedWriter.newLine();
            bufferedWriter.write("Stack Trace:");
            for (StackTraceElement element : exception.getStackTrace()) {
                bufferedWriter.newLine();
                bufferedWriter.write("\tat " + element.toString());
            }

            // 메서드명 및 쓰레드 정보 추가
            bufferedWriter.newLine();
            bufferedWriter.write("Thread: " + Thread.currentThread().getName());
            bufferedWriter.newLine();
            bufferedWriter.write("*******************************************************************************************************************");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
