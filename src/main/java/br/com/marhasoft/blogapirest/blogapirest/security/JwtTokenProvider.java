package br.com.marhasoft.blogapirest.blogapirest.security;

import br.com.marhasoft.blogapirest.blogapirest.exceptions.BlogAppException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private String jwtExpirationInMs;

    public String generationToken(Authentication authentication) {
        String username = authentication.getName();
        Date dateNow = new Date();
        Date dateExpiration = new Date(dateNow.getTime() + jwtExpirationInMs);

        String token = Jwts.builder().setSubject(username)
                .setIssuedAt(new Date()).setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

        return token;
    }

    public String getUsernameOfToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    //Valida tudo sobre o token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token);
            return true;
        } catch (SignatureException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Assinatura JWT inválida");
        } catch (MalformedJwtException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT inválido");
        } catch (ExpiredJwtException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT expirado");
        } catch (UnsupportedJwtException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT não compatível");
        } catch (IllegalArgumentException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT está vazio");
        }
    }
}
