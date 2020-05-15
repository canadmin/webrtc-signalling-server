package com.dualchat.dualchat.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenManager {
    private static final int validity = 5 * 60 * 20000;
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String username) {
        return "Bearer " + Jwts.builder()
                .setSubject(username)
                .signWith(key)
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .compact();
    }

    public boolean tokenValidate(String token) {
        return getUsernameToken(token) != null && isExpired(token);
    }

    public String getUsernameToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public boolean isExpired(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token).getBody();
    }
}
