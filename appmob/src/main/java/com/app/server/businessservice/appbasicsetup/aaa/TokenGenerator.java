package com.app.server.businessservice.appbasicsetup.aaa;import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;
import com.spartan.server.interfaces.TokenClaimBeanInterface;
import com.spartan.server.interfaces.TokenInitializerInterface;

@Service
public class TokenGenerator implements TokenInitializerInterface {

	@Override
	public String getToken(final TokenClaimBeanInterface tokenClaimBean) {
		/****
		 * Set signature algorithm
		 */
		final SignatureAlgorithm signatureAlgorithm = getAlgorhythm(tokenClaimBean.getAlgorithm());

		/****
		 * We will sign our JWT with our ApiKey secret
		 */
		final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(tokenClaimBean.getKey());
		final Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		/****
		 * Let's set the JWT Claims
		 */
		final JwtBuilder builder = Jwts.builder().signWith(signatureAlgorithm, signingKey).setClaims(tokenClaimBean.getClaimAttributes());

		/****
		 * Builds the JWT and serializes it to a compact, URL-safe string
		 */
		return builder.compact();
	}

	private SignatureAlgorithm getAlgorhythm(final String algoType) {
		switch (algoType) {
		case "HS256":
			return SignatureAlgorithm.HS256;
		case "HS384":
			return SignatureAlgorithm.HS384;
		case "HS512":
			return SignatureAlgorithm.HS512;
		case "RS256":
			return SignatureAlgorithm.RS256;
		case "RS384":
			return SignatureAlgorithm.RS384;
		case "RS512":
			return SignatureAlgorithm.RS512;
		case "ES256":
			return SignatureAlgorithm.ES256;
		case "ES384":
			return SignatureAlgorithm.ES384;
		case "ES512":
			return SignatureAlgorithm.ES512;
		case "PS256":
			return SignatureAlgorithm.PS256;
		case "PS384":
			return SignatureAlgorithm.PS384;
		}
		return null;
	}

}
