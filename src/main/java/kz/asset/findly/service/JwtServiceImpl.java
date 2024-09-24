package kz.asset.findly.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kz.asset.findly.model.entity.User;

@Service
public class JwtServiceImpl implements JwtService {
	@Value("${token.signing.key}")
	private String jwtSigningKey;
	
	@Override
	public Integer extractId(String token) {
        return extractClaim(token, (claims) -> Integer.valueOf(claims.getSubject()));
	}

	@Override
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> extraClaims = new HashMap<>();
		
		extraClaims.put("roles", userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.toList()
				);
		
        return generateToken(extraClaims, userDetails);
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final Integer id = extractId(token);
        
		return (id.equals(((User) userDetails).getId())) && !isTokenExpired(token);
	}
	
	private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
        		.setClaims(extraClaims)
        		.setSubject(((User) userDetails).getId().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 2))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
		Claims claims = extractAllClaims(token);
		
		return claimsResolvers.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
