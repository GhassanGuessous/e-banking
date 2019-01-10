/**
 * 
 */
package org.ebanking.security;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author Ghassan
 *
 */
@Component
public class JwtTokenUtil implements Serializable{

	public String getUsernameFromToken(String jwtToken) {
		Claims claims = Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX, ""))
				.getBody();
		
		String username = claims.getSubject();
		
		return username;
	}
}
