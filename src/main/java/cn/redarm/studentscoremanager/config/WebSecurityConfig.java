package cn.redarm.studentscoremanager.config;

import cn.redarm.studentscoremanager.filter.JwtFilter;
import cn.redarm.studentscoremanager.model.entity.User;
import cn.redarm.studentscoremanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author redarm
 * @Date 2020/6/19 3:36 下午
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                User user = null;

                user = userRepository.findByUsername(s);

                return user;
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtFilter jwtTokenFilter() {
        return new JwtFilter();
    }

    /**
     * @return void
     * @Author redarm
     * @Description //TODO
     * @Date 3:59 下午 2020/6/19
     * @Param [auth]
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())

                .passwordEncoder(passwordEncoder());
    }

    /**
     * @return void
     * @Author redarm
     * @Description //TODO config springsecurity and add token filter
     * @Date 4:00 下午 2020/6/19
     * @Param [http]
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()

                .authorizeRequests()

                .antMatchers("/**").permitAll()

                .anyRequest().authenticated();

        http
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http
                .headers().cacheControl();
    }

}
