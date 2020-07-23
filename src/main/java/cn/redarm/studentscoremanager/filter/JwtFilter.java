package cn.redarm.studentscoremanager.filter;

import cn.redarm.studentscoremanager.model.entity.User;
import cn.redarm.studentscoremanager.repository.UserRepository;
import cn.redarm.studentscoremanager.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author redarm
 * @Date 2020/6/19 3:53 下午
 **/
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtTokenUtils;

    /**
     * @return void
     * @Author redarm
     * @Description //TODO get user from token if it has and add it to authentication
     * @Date 3:56 下午 2020/6/19
     * @Param [httpServletRequest, httpServletResponse, filterChain]
     **/
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(JwtUtil.TOKEN_HEADER);
        log.debug("token: " + header);

        if (!StringUtils.isEmpty(header) && header.startsWith(JwtUtil.TOKEN_PREFIX)) {
            String token = header.substring(JwtUtil.TOKEN_PREFIX.length());

            User user = userRepository.findByUsername(jwtTokenUtils.getUsernameFromToken(token));

            if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
