package com.lucianopaoletti.seguro.authentication.env;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StreamUtils;

public class DocketSecretsProcessor implements EnvironmentPostProcessor {
	
private final String TOKEN_SECRETKEY_FILE_PROPERTY = "TOKEN_SECRETKEY_FILE";
private final String DATASOURCE_PASSWORD_FILE_PROPERTY = "DATASOURCE_PASSWORD_FILE"; 
	
	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		System.out.println("SECRETS POST PROCESSOR...");
		
		this.processTokenSecrets(environment, application);
		this.processDbSecrets(environment, application);
	}

	private void processTokenSecrets(ConfigurableEnvironment environment, SpringApplication application) {
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
	
	private void processDbSecrets(ConfigurableEnvironment environment, SpringApplication application) {
		var dbPasswordRoute = System.getenv(DATASOURCE_PASSWORD_FILE_PROPERTY);
		if (dbPasswordRoute == null || dbPasswordRoute.isBlank()) {
			System.out.println("No database password route property");
			return;
		}
		
		var resource = new FileSystemResource(dbPasswordRoute);
		if (!resource.exists()) {
			System.out.println("Database password file not found");
			return;
		}
		
		try {
			var dbPassword = StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
			var dbProps = new Properties();
			dbProps.put("spring.datasource.password", dbPassword);
			environment.getPropertySources().addLast(new PropertiesPropertySource("dbProps", dbProps));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
