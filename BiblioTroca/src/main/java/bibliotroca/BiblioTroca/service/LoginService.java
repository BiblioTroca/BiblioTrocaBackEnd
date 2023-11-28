package bibliotroca.BiblioTroca.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import bibliotroca.BiblioTroca.entity.User;

@Service
public class LoginService {
	public String generateToken(User user, String picture) {
		try {
		    var algorithm = Algorithm.HMAC256("teste");
		    return JWT.create()
		        .withIssuer("BiblioTroca-API")
		        .withSubject(user.getEmail())
		        .withClaim("firstName", user.getName())
		        .withClaim("lastName", user.getSurname())
		        .withClaim("email", user.getEmail())
		        .withClaim("picture", picture)
		        .withExpiresAt(expirationDate())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
			throw new RuntimeException("erro gerar token", exception);
		}
	}
	
	private Instant expirationDate() {
		return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
	}
	
	private String returnSubject(String token) {
		try {
		    var algorithm = Algorithm.HMAC256("teste");
		    return JWT.require(algorithm)
		        .withIssuer("BiblioTroca-API")
		        .build()
		        .verify(token)
		        .getSubject();
		        
		} catch (JWTVerificationException exception){
			throw new RuntimeException("token invalido", exception);
		}
	}
}
