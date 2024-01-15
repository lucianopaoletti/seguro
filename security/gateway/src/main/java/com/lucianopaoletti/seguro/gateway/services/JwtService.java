package com.lucianopaoletti.seguro.gateway.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianopaoletti.seguro.gateway.configuration.TokenProperties;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Service
public class JwtService {
	
	// -------------------------------------------------------------------------------------------------------
	// Atributos
	
	private TokenProperties tokenProperties;
	
	// -------------------------------------------------------------------------------------------------------
	// Constructores
	
	@Autowired
	public JwtService(TokenProperties tokenProperties) {
		this.tokenProperties = tokenProperties;
	}
	
	// -------------------------------------------------------------------------------------------------------
	// Metodos

	public String validateToken(String token) 
			throws ExpiredJwtException, MalformedJwtException, UnsupportedJwtException, SignatureException {
		var secretKeyEncoded = this.tokenProperties.getSecretKey();
	    var secretKeyDecoded = Decoders.BASE64.decode(secretKeyEncoded);
	    var secretKey = Keys.hmacShaKeyFor(secretKeyDecoded);
		
		return Jwts.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token)
			.getPayload()
			.getSubject();
	}

}
