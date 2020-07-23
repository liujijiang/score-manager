package cn.redarm.studentscoremanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author redarm
 * @Date 2020/6/19 4:49 下午
 **/
@Configuration
@EnableWebMvc
public class CrosConfig implements WebMvcConfigurer {

    /**
     * @return void
     * @Author redarm
     * @Description //TODO enable cros
     * @Date 4:51 下午 2020/6/19
     * @Param [registry]
     **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("*")
                .maxAge(3600);
    }
}
