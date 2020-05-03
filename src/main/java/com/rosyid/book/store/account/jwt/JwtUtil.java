package com.rosyid.book.store.account.jwt;

import com.rosyid.book.store.account.service.implement.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil
{
//    protected final Logger logger = LoggerFactory.getLogger((JwtUtil.class));
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${rosyid.app.jwtSecret}")
    private String jwtSecret;

    @Value("${rosyid.app.jwtExpirationMs}")
    private int jwtExpirationMs;


    /**
     * Generate Jwt Token
     * @param authentication
     * @return
     */
    public String generateJwtToken(Authentication authentication)
    {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject( (userPrincipal.getUsername()) )
                .setIssuedAt(new Date() )
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs) )
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    /**
     * Get UserName from Jwt Token
     * @param token
     * @return
     */
    public String getUserNameFromJwtToken(String token)
    {
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    /**
     * Check validate a token
     * Handeled by io.jsonwebtoken.JwtParser public abstract JwtParser setSigningKey(String s)
     * @param authToken
     * @return
     */
    public boolean validateJwtToken(String authToken)
    {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(authToken);

            return true;

        } catch (SignatureException e)
        {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e)
        {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e)
        {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e)
        {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e)
        {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
