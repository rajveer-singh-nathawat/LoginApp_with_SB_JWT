package com.example.demo.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TokenInfo;
import com.example.demo.repository.UserReository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	@Autowired
	private UserReository userReository;
	private String SECRET_KEY = "secret";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		TokenInfo tokenInfo = userReository.findTokenInfo(userDetails.getUsername());
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", tokenInfo.getUserId());
		claims.put("userName", tokenInfo.getUserName());
		claims.put("fullName", tokenInfo.getFullName());
		claims.put("role", tokenInfo.getRoles());
		
		return createToken(claims);
	}

	private String createToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setSubject(claims.get("userName").toString()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (!isTokenExpired(token) && username.equals(userDetails.getUsername()));
	}
}
