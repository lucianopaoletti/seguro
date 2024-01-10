package com.lucianopaoletti.seguro.authentication.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
	
	// -------------------------------------------------------------------------
	// Atributos
	
	private BCryptPasswordEncoder encoder;
	private final int encryptionStrength = 10;
	
	// -------------------------------------------------------------------------
	// Constructores
	
	public EncryptionService() {
		this.encoder = new BCryptPasswordEncoder(encryptionStrength);
	}
	
	// -------------------------------------------------------------------------
	// Metodos
	
	public String encrypt(String toEncrypt) {
		return this.encoder.encode(toEncrypt);
	}
	
	public boolean matches(String a, String b) {
		return this.encoder.matches(a, b);
	}

}
