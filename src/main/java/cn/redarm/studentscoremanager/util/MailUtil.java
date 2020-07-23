package cn.redarm.studentscoremanager.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @Author redarm
 * @Date 2020/6/19 1:09 下午
 **/
@Component
@Slf4j
public class MailUtil {

    @Autowired
    private JavaMailSenderImpl sender;

    @Value("${spring.mail.username}")
    private String mailUsername;

    private Random random = new Random(99);

    /**
     * @Author Redarm
     * @Description //TODO send a message
     * @Date 12:13 下午 2020/7/7
     * @Param [mailAdress]
     * @return java.lang.String
    **/
    public String sendMessage(String mailAdress, String msg) {
        if (StringUtils.isEmpty(mailAdress)) {
            return null;
        }
        if (!mailAdress.contains("@")) {
            return null;
        }

        String mailType = mailAdress.substring(mailAdress.lastIndexOf("@"));

        if (!isTypeRight(mailType)) {
            return null;
        }
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setSubject("山东理工大学学生成绩管理系统");
        mailMessage.setFrom(mailUsername);
        mailMessage.setTo(mailAdress);

        mailMessage.setText(msg);

        try {
            sender.send(mailMessage);
        } catch (Exception e) {
            log.warn("mail send error" + e);
            return null;
        }

        return msg;
    }

    /**
     * @Author Redarm
     * @Description //TODO judge the email type
     * @Date 12:14 下午 2020/7/7
     * @Param [emailType]
     * @return java.lang.Boolean
    **/
    public Boolean isTypeRight(String emailType) {
        switch (emailType) {
            case "@163.com":
            case "@gmail.com":
            case "@hotmail.com":
            case "@yahoo.com":
            case "@sina.com":
            case "@sohu.com":
            case "@qq.com":
            case "@126.com":
            case "@yeah.com":
                return true;
            default:
                return false;
        }
    }
}
