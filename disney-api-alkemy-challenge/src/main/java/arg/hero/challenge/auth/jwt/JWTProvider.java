package arg.hero.challenge.auth.jwt;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

import io.jsonwebtoken.security.Keys;

@Component
public class JWTProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(JWTProvider.class);
	
	SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public String generateToken(Authentication authentication) {
		User principal = (User) authentication.getPrincipal();
		return Jwts.builder()
					.setSubject(principal.getUsername())
					.signWith(key)
					.compact();
	}

	public boolean validate(String token) {
		try {
			Jwts.parser()
			.setSigningKey(key)
			.parseClaimsJws(token);
			return true;
		} catch(MalformedJwtException e1) {
			logger.error("Malformed Token");
		} catch(UnsupportedJwtException e2) {
			logger.error("Unsupported Token");
		} catch(IllegalArgumentException e3) {
			logger.error("Illegal Argument Token");
		}
		
		return false;
	}

	public String getUsernameFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}



}
