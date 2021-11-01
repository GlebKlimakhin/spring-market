package com.gb.data.springdata.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {

    @Value("$(jwt.secret)")
    private String jwtSecret;

    public String generateToken(String login){
        Date date = Date.from(LocalDate.now().minusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }
        catch (Exception e) {
            log.error("invalid token");
        }
        return false;
    }

    public String getLoginFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(token).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


}
