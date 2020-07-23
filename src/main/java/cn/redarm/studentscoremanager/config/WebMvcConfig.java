package cn.redarm.studentscoremanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author redarm
 * @Date 2020/6/19 5:05 下午
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * @return void
     * @Author redarm
     * @Description //TODO handle swagger2 can't display
     * @Date 5:47 下午 2020/6/19
     * @Param [registry]
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
