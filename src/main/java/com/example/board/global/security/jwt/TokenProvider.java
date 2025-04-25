package com.example.board.global.security.jwt;

import com.example.board.member.entity.Member;
import com.example.board.member.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log4j2
public class TokenProvider {

  private final JwtProperties jwtProperties;

  public String generateAccessToken(Member member, Duration duration) {
    Date now = new Date();

    return makeAccessToken(new Date(now.getTime() + duration.toMillis()), member);
  }

  public String generateRefreshToken(Member member, Duration duration) {
    Date now = new Date();

    return makeRefreshToken(new Date(now.getTime() + duration.toMillis()), member);
  }

  private String makeAccessToken(Date expiry, Member member) {
    Date now = new Date();

    return Jwts.builder()
        .setHeaderParam("typ", "JWT")
        .setIssuer(jwtProperties.getIssuer())
        .setIssuedAt(now)
        .setExpiration(expiry)
        .setSubject(member.getEmail())
        .claim("id", member.getId())
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private String makeRefreshToken(Date expiry, Member member) {
    Date now = new Date();

    return Jwts.builder()
        .setHeaderParam("typ", "JWT")
        .setIssuer(jwtProperties.getIssuer())
        .setIssuedAt(now)
        .setExpiration(expiry)
        .setSubject(member.getEmail())
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean validToken(String token) {
    try {
      log.info("Validating token: {}", token);

      Jwts.parserBuilder()
          .setSigningKey(getSigningKey())
          .build()
          .parseClaimsJws(token);

      return true;
    } catch (io.jsonwebtoken.security.SecurityException | io.jsonwebtoken.MalformedJwtException e) {
      log.error("잘못된 JWT 서명입니다.");
    } catch (io.jsonwebtoken.ExpiredJwtException e) {
      log.error("만료된 JWT 토큰입니다.");
    } catch (io.jsonwebtoken.UnsupportedJwtException e) {
      log.error("지원되지 않는 JWT 토큰입니다.");
    } catch (IllegalArgumentException e) {
      log.error("JWT 토큰이 잘못되었습니다.");
    }
    return false;
  }

  public UsernamePasswordAuthenticationToken getAuthentication(String token) {
    Claims claims = getClaims(token);
    Set<SimpleGrantedAuthority> authorities = Collections.singleton(
        new SimpleGrantedAuthority(Role.MEMBER.getValue()));

    return new UsernamePasswordAuthenticationToken(
        new User(claims.getSubject(), "",
            authorities),
        token,
        authorities
    );
  }

  public Claims getClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
  }

}