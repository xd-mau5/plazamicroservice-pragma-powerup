package com.pragma.powerup.plazamicroservice.configuration.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    @Value("${jwt.secret}")
    private String secret;

    public UserDetails getUserDetailsFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token).getBody();
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> authorities = new ArrayList<>();
                String role = claims.get("role", String.class);
                authorities.add((GrantedAuthority) () -> role);
                return authorities;
            }
            @Override
            public String getPassword() {
                return null;
            }
            @Override
            public String getUsername() {
                return claims.getSubject();
            }
            @Override
            public boolean isAccountNonExpired() {
                return false;
            }
            @Override
            public boolean isAccountNonLocked() {
                return false;
            }
            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }
            @Override
            public boolean isEnabled() {
                return false;
            }
        };
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Malformed token");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported token");
        } catch (ExpiredJwtException e) {
            logger.error("Expired token");
        } catch (IllegalArgumentException e) {
            logger.error("Empty token");
        } catch (SecurityException e) {
            logger.error("Invalid signature");
        }
        return false;
    }
}
