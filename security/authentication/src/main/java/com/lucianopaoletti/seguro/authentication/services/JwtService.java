package com.lucianopaoletti.seguro.authentication.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.authentication.configuration.TokenProperties;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	// -------------------------------------------------------------------------------------
	// Atributos
	
	private TokenProperties tokenProperties;
	
	// -------------------------------------------------------------------------------------
	// Constructores
	
	@Autowired
	public JwtService(TokenProperties tokenProperties) {
		this.tokenProperties = tokenProperties;
	}
	
	// -------------------------------------------------------------------------------------
	// Metodos
	
	public String generateJws(String username) {
		var secretKeyEncoded = this.tokenProperties.getSecretKey();
	    var secretKeyDecoded = Decoders.BASE64.decode(secretKeyEncoded);
	    var secretKey = Keys.hmacShaKeyFor(secretKeyDecoded);
	    
	    var currentLocalDate = LocalDate.now();
	    var expirationLocalDate = currentLocalDate.plusDays(this.tokenProperties.getExpirationDays());
	    
	    var currentDate = Date.from(currentLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    var expirationDate = Date.from(expirationLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    
	    return Jwts.builder()
	            .subject(username)
	            .issuedAt(currentDate)
	            .expiration(expirationDate)
	            .signWith(secretKey)
	            .compact();
	}
	
	public String generateSecretKey() {
		var secretKey = Jwts.SIG.HS256.key().build();
		var secretEncoded = Encoders.BASE64.encode(secretKey.getEncoded());
		
		return secretEncoded;
	}

}
