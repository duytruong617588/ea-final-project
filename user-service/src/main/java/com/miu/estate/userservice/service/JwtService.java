package com.miu.estate.userservice.service;

import com.miu.estate.userservice.exception.InvalidTokenException;
import com.miu.estate.userservice.model.User;
import com.miu.estate.userservice.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final UserRepository userRepository;
    private String secret = "8d4fed75477d160c393db8a22edce23a5ae7971b4533077d89ac0016dd92c879d21791073310294924cb896443a8214cfdc129baa42af8b3030a397382a93532";
    private long jwtExpiration = 86400000;
    private long refreshExpiration = 864000000;

    public String generateToken(User user){
        Map<String, Object> claims = Map.of("user", user);
        user.setCreatedAt(null);
        user.setUpdatedAt(null);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public User getUserFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken == null || !bearerToken.startsWith("Bearer ")){
            throw new RuntimeException("Please enter a valid token");
        }

        String jwtToken= bearerToken.substring(7);
        String userEmail = extractUsername(jwtToken);
        return userRepository.findByEmailAndDeletedAtIsNull(userEmail).orElse(null);
    }

    public String extractUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new InvalidTokenException("Invalid token");
        } catch (ExpiredJwtException ex) {
            throw new InvalidTokenException("Expired token");
        } catch (UnsupportedJwtException ex) {
            throw new InvalidTokenException("Unsupported token");
        } catch (IllegalArgumentException ex) {
            throw new InvalidTokenException("Illegal token");
        }
    }

    private String buildToken(
            Map<String, Object> extractClaims,
            UserDetails userDetails,
            long expiration

    ){
        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private Key getSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateVerificationToken(String email){
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
