package utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtil {

    public static Logger getLogger(Class<?> className) {
        Logger logger = Logger.getLogger(className.getName());
        try {
            // 로그 파일 핸들러 설정
            FileHandler fileHandler = new FileHandler("calc_bonus.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logger;
    }
}
