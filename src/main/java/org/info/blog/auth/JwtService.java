package org.info.blog.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY="4Vcn/Gck0s8vKUhJYNkKg1uMKuYvphYpc+gu2njK1Fttv479SDWOdrA8FOxLSlYy\n";
    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims =extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJwt(jwtToken)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
