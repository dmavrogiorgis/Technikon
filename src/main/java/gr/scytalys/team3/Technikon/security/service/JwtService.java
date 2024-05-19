package gr.scytalys.team3.Technikon.security.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface JwtService {
    String generateToken(String userName, String tin, String email, long id);
    String extractUsername(String token);
    String extractTin(String token);
    String extractEmail(String token);
    long extractId(String token);
    Date extractExpiration(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    Boolean validateToken(String token, UserDetails userDetails);
}
