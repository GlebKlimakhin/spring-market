package com.gb.data.springdata.security.jwt;

import com.gb.data.springdata.security.CustomUserDetails;
import com.gb.data.springdata.security.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Component
@Slf4j
public class JwtFilter implements Filter {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("do filter...");
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if(token != null && jwtProvider.validateToken(token)) {
            String login = jwtProvider.getLoginFromToken(token);
            CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(login);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request){
        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(hasText(bearer) && bearer.startsWith("Bearer ")){
            return bearer.substring(7);
        }
            return null;
    }
}
