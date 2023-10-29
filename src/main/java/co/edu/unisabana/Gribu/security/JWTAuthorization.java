package co.edu.unisabana.Gribu.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthorization {

  protected static final String SECRET = "DFGW%#$AR@#ASD%^/.,]";

  public String getJWTToken(String username) {
    List<GrantedAuthority> grantedAuthorities = AuthorityUtils
        .commaSeparatedStringToAuthorityList("ROLE_USER");

    return Jwts
        .builder()
        .setSubject(username)
        .claim("authorities",
            grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 3600000))
        .signWith(SignatureAlgorithm.HS512,
            SECRET.getBytes()).compact();
  }
}