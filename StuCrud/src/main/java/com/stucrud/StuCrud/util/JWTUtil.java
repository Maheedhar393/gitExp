package com.stucrud.StuCrud.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {

    private final String SECRET_KEY = "my-super-secret-key-that-is-long-enough-1234567890";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    private final  long EXPIRATION_TIME = 1000*60*60; // 1 hour in milliseconds

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token){
       Claims body = getClaims(token, key);
       
       return body.getSubject();
                
    }

    private static Claims getClaims(String token, SecretKey key) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(UserDetails userDetails,String token, String username){
       return username.equals(userDetails.getUsername())  && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        Claims claims = getClaims(token, key);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }
}
