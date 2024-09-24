package kz.asset.findly.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
	public Integer extractId(String token);
	public String generateToken(UserDetails userDetails);
	public boolean isTokenValid(String token, UserDetails userDetails);
}
