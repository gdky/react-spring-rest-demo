package com.gdky.rest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.gdky.rest.configuration.Constants;

@Component
public class TokenUtils {

	private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

	private String secret = Constants.TOKEN_SECRET;

	private Integer expiration = Constants.TOKEN_EXPIRATION;

	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public String getPasswordFromToken(String token) {
		String password;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			password = (String) claims.get("password");
		} catch (Exception e) {
			password = null;
		}
		return password;
	}

	public Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			created = new Date((Long) claims.get("created"));
		} catch (Exception e) {
			created = null;
		}
		return created;
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	public String getAudienceFromToken(String token) {
		String audience;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			audience = (String) claims.get("audience");
		} catch (Exception e) {
			audience = null;
		}
		return audience;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(this.secret)
					.parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	private Date generateCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + this.expiration * 1000);
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = this.getExpirationDateFromToken(token);
		return expiration.before(this.generateCurrentDate());
	}

	private Boolean isCreatedBeforeLastPasswordReset(Date created,
			Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("sub", userDetails.getUsername());
		claims.put("created", this.generateCurrentDate());
		claims.put("password", userDetails.getPassword());
		return this.generateToken(claims);
	}

	private String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims)
				.setExpiration(this.generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, this.secret).compact();
	}

	public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
		final Date created = this.getCreatedDateFromToken(token);
		return (!(this.isCreatedBeforeLastPasswordReset(created,
				lastPasswordReset)) && !(this.isTokenExpired(token)));
	}

	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			claims.put("created", this.generateCurrentDate());
			refreshedToken = this.generateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		CustomUserDetails user = (CustomUserDetails) userDetails;
		final String username = this.getUsernameFromToken(token);
		final String password = this.getPasswordFromToken(token);
		return (username.equals(user.getUsername())
				&& !(this.isTokenExpired(token)) && password.equals(user
				.getPassword()));
	}
}
