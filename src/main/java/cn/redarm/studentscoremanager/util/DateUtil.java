package cn.redarm.studentscoremanager.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author redarm
 * @Date 2020/6/19 3:14 下午
 **/
@Component
public class DateUtil {

    private static LocalDateTime dateTime;

    private static String DATE_FORMAT = "yyyy-MM-dd HH-mm-ss";

    public static String now() {
        dateTime = LocalDateTime.now();
        return dateTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

}
