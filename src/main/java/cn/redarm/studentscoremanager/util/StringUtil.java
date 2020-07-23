package cn.redarm.studentscoremanager.util;

import org.thymeleaf.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author redarm
 * @Date 2020/6/25 10:10 上午
 **/
public class StringUtil {

    /**
     * @return java.lang.Boolean
     * @Author redarm
     * @Description //TODO judge string is number
     * @Date 10:13 上午 2020/6/25
     * @Param [str]
     **/
    public static Boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+(\\\\.[0-9]+)?");
        Matcher matcher = pattern.matcher(str);

        if (matcher.matches()) {
            return true;
        }

        return false;
    }
    
    /**
     * @Author Redarm
     * @Description //TODO remove string square brackets
     * @Date 3:47 下午 2020/7/6
     * @Param [str]
     * @return java.lang.String
    **/
    public static String removeSQ(String str){
        if (StringUtils.isEmpty(str)){
            return "";
        }

        if (!str.contains("[")){
            return str;
        }
        return str.substring(2, str.length() - 2);
    }
}
