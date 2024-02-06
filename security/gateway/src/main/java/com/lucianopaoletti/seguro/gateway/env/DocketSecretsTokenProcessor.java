package com.lucianopaoletti.seguro.gateway.env;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StreamUtils;

public class DocketSecretsTokenProcessor implements EnvironmentPostProcessor {
	
	private final String TOKEN_SECRETKEY_FILE_PROPERTY = "TOKEN_SECRETKEY_FILE";
	
	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		System.out.println("SECRETS POST PROCESSOR...");
		
		var secretKeyRoute = System.getenv(TOKEN_SECRETKEY_FILE_PROPERTY);
		if (secretKeyRoute == null || secretKeyRoute.isBlank()) {
			System.out.println("No token secret key route property");
			return;
		}
		
		var resource = new FileSystemResource(secretKeyRoute);
		if (!resource.exists()) {
			System.out.println("Token secret key file not found");
			return;
		}
		
		try {
			var secretKey = StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
			var tokenProps = new Properties();
			tokenProps.put("token.secretKey", secretKey);
			environment.getPropertySources().addLast(new PropertiesPropertySource("tokenProps", tokenProps));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
