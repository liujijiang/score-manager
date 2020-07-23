package cn.redarm.studentscoremanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class StudentScoreManagerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.setProperty("spring.config.additional-location", "file:${user.home}/.scoreManager/");

        SpringApplication.run(StudentScoreManagerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        System.setProperty("spring.config.additional-location", "file:${user.home}/.scoreManager/");
        return application.sources(StudentScoreManagerApplication.class);
    }

}
