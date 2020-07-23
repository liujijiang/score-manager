package cn.redarm.studentscoremanager.stringUtil;

import cn.redarm.studentscoremanager.util.StringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author redarm
 * @Date 2020/6/25 10:13 上午
 **/
@SpringBootTest
public class StringUtilTest {

    @Test
    public void test1() {
        String str = "123123";
        String str1 = "123liu123";

        System.out.println(StringUtil.isNumber(str));
        System.out.println(StringUtil.isNumber(str1));
    }
}
