package com.mangalaxy.mango.security.jwt;

import com.mangalaxy.mango.security.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

  @Value("${security.jwt.issuer}")
  private String jwtIssuer;
  /**
   * Define your signing JWT secret key.
   */
  @Value("${security.jwt.secret-key}")
  private String jwtSecret;

  /**
   * Define how long your token will live.
   */
  @Value("${security.jwt.ttl}")
  private Duration jwtTimeToLive;

  /**
   * Generate JWT token.
   *
   * @param authentication token from SecurityContext
   * @return generated JWT token
   */
  public String generateToken(Authentication authentication) {
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    Instant now = Instant.now(Clock.systemDefaultZone());
    Instant expirationDate = now.plus(jwtTimeToLive);

    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret);
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    return Jwts.builder()
          .setId(String.valueOf(principal.getId()))
          .setIssuer(jwtIssuer)
          .setSubject(principal.getUsername())
          .setAudience(principal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")))
          .setIssuedAt(Date.from(now))
          .setExpiration(Date.from(expirationDate))
          .signWith(signatureAlgorithm, signingKey)
          .compact();
  }

  public Long getUserId(String token) {
    return Long.parseLong(decodeJWT(token).getSubject());
  }

  public String getUsername(String token) {
    Claims claims = decodeJWT(token);
    return claims.getSubject();
  }

  public String getUserRole(String token) {
    return decodeJWT(token).getAudience();
  }

  public boolean validateToken(String jwtToken) {
    try {
      decodeJWT(jwtToken);
      return true;
    } catch (SignatureException ex) {
      log.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty.");
    }
    return false;
  }

  private Claims decodeJWT(String token) {
    return Jwts.parser()
          .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
          .parseClaimsJws(token)
          .getBody();
  }
}
