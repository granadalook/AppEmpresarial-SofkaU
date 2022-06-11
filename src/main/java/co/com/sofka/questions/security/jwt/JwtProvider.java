package co.com.sofka.questions.security.jwt;

import co.com.sofka.questions.security.collections.UserMajor;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Integer expiration;

    public String generateToken(Authentication authentication) {
        UserMajor userMajor = (UserMajor) authentication.getPrincipal();
        return Jwts.builder().setSubject(userMajor.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex) {
            logger.error("Firma JWT no valida");
        }
		catch (MalformedJwtException ex) {
            logger.error("Token JWT no valida");
        }
		catch (ExpiredJwtException ex) {
            logger.error("Token JWT caducado");
        }
		catch (UnsupportedJwtException ex) {
            logger.error("Token JWT no compatible");
        }
		catch (IllegalArgumentException ex) {
            logger.error("La cadena claims JWT esta vacia");
        }
        return false;
    }
}
