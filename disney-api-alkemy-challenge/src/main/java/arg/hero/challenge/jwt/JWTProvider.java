package arg.hero.challenge.jwt;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTProvider {
	
	private String secret = "mySuperMegaSecretKeyWhichShouldNotBeHereButIDontKnowWHereElseToPutItYetButSinceItsAChallengeIJustMightJustLeaveItHere";

	private SecretKey getSigningKey() {
	  byte[] keyBytes = Decoders.BASE64.decode(this.secret);
	  return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(Authentication authentication) {
		
		User principal = (User) authentication.getPrincipal();
		return Jwts.builder()
					.setSubject(principal.getUsername())
					.signWith(getSigningKey())
					.compact();
	}

	public boolean validate(String token) {
		Jwts.parser()
			.setSigningKey(getSigningKey())
			.parseClaimsJws(token);
		return true;
	}

	public String getUsernameFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(getSigningKey())
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}



}
