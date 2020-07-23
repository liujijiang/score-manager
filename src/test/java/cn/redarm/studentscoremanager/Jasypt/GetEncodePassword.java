package cn.redarm.studentscoremanager.Jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author redarm
 * @Date 2020/6/19 12:36 下午
 **/
@SpringBootTest
public class GetEncodePassword {

    @Autowired
    private StringEncryptor encryptor;

    /**
     * @Author Redarm
     * @Description //TODO 配置jasypt， 拿到加密后的密码，把加密后的密文配置到application.yml
     * @Date 3:30 下午 2020/7/23
     * @Param []
     * @return void
    **/
    @Test
    public void getPassword() {
        System.out.println(encryptor.encrypt("your password"));
    }
}
