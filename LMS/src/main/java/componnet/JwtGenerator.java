package componnet;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtGenerator {
    private String secret = "L2hKlYxot8gS1RKiXoBbKLOl99T6ftfWGe05ar5HiyI="; // Secret key for signing the token

    // Method to generate the token, only using the role
    public String generateToken(String role) {
        return Jwts.builder()
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours validity
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Method to extract the role from the token
    public String extractRole(String token) {
        String role =  extractClaim(token, claims -> claims.get("role", String.class));
        System.out.println(role);
        return role;
        // Extract the role claim
    }

    // Method to validate the token and ensure it's not expired and the role matches
    public boolean validateToken(String token, String role) {
        return extractRole(token).equals(role) && !isTokenExpired(token);
    }

    // Helper method to check if the token is expired
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    // Helper method to extract claims from the token
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }
}
