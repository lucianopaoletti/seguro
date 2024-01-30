package com.lucianopaoletti.seguro.gateway.filters;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.lucianopaoletti.seguro.gateway.services.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ValidateTokenGatewayFilterFactory
		extends AbstractGatewayFilterFactory<ValidateTokenGatewayFilterFactory.Config> {
	
	// -------------------------------------------------------------------------------------------------------
	// Atributos
	
	JwtService jwtService;
	
	public static class Config {
	}
	
	// -------------------------------------------------------------------------------------------------------
	// Constructores

	@Autowired
	public ValidateTokenGatewayFilterFactory(JwtService jwtService) {
		super(Config.class);
		
		this.jwtService = jwtService;
	}
	
	// -------------------------------------------------------------------------------------------------------
	// Metodos

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			var authHead = exchange.getRequest().getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION);
			if (authHead.isEmpty()) {
				var errorMsg = "Debe loggearse e ingresar el token obtenido en el header.";
				return generateErrorResponse(exchange, errorMsg);
			}

			var token = authHead.get(0).replace("Bearer", "").trim();
			if (token.isEmpty() || token.isBlank()) {
				var errorMsg = "Debe loggearse e ingresar el token obtenido en el header.";
				return generateErrorResponse(exchange, errorMsg);
			}

			String subject;
			try {
				subject = this.jwtService.validateToken(token);
			} catch (ExpiredJwtException e) {
				var errorMsg = "El token ha expirado. Por favor, vuelva a loggearse nuevamente.";
				return generateErrorResponse(exchange, errorMsg);
			} catch (MalformedJwtException | SignatureException | UnsupportedJwtException e) {
				var errorMsg = "El token ingresado es invalido.";
				e.printStackTrace();
				return generateErrorResponse(exchange, errorMsg);
			} catch (Exception e) {
				var errorMsg = "Se ha producido un error al validar el token";
				e.printStackTrace();
				return generateErrorResponse(exchange, errorMsg);
			}

			var request = exchange.getRequest()
					.mutate()
					.header("userId", subject)
					.build();

			var mutatedExchange = exchange.mutate()
					.request(request)
					.build();

			return chain.filter(mutatedExchange);
		});
	}

	private Mono<Void> generateErrorResponse(ServerWebExchange exchange, String errorMsg) {
		var json = String.format("{\"error\": \"%s\"}", errorMsg);
		var contentRaw = json.getBytes(StandardCharsets.UTF_8);
		var contentBuffer = exchange.getResponse().bufferFactory().wrap(contentRaw);
		exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		return exchange.getResponse().writeWith(Flux.just(contentBuffer));
	}

}
