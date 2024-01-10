package com.lucianopaoletti.seguro.authentication.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@ConfigurationProperties(prefix = "token")
@Validated
public class TokenProperties {
	
	@NotEmpty
    private String secretKey;
    
    @Digits(fraction = 0, integer = 10)
    @Min(1)
    private int expirationDays;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

	public int getExpirationDays() {
		return expirationDays;
	}

	public void setExpirationDays(int expirationDays) {
		this.expirationDays = expirationDays;
	}

}
