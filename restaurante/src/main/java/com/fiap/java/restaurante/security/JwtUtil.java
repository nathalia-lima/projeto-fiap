package com.fiap.java.restaurante.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import java.util.Date;

import com.fiap.java.restaurante.models.Usuario;


public class JwtUtil {
    private static final String SECRET_KEY = "o6paeLufEr3uXd4RQSBKXwrDUaUEtfoLJ3d6CPV4YJJsZSESlHu2T2HGRzoCLYi5";
    private static final long EXPIRATION_TIME = 3600000; 

    public static String generateToken(String username, String perfilUsuario) {
        return Jwts.builder()
            .setSubject(username)
            .claim("role", perfilUsuario) 
            .claim("issuer", "Restaurante API")
            
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }

    public static boolean validateToken(String token, String username) {
        String tokenUsername = getUsername(token);
        String tokenIssuer = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody()
            .getIssuer();
        return (tokenUsername.equals(username) && !isTokenExpired(token) && tokenIssuer.equals("Restaurante API"));
    }

    public static String getRole(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();
        return claims.get("role", String.class);
    }
    

    public static String getUsername(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();
        return claims.getSubject();
    }

    public static boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();
        return claims.getExpiration().before(new Date());
    }
}