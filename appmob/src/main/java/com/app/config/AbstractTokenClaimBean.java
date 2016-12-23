package com.app.config;
import java.util.Date;
import java.util.HashMap;

import com.spartan.server.interfaces.TokenClaimBeanInterface;

//@Component
public abstract class AbstractTokenClaimBean implements TokenClaimBeanInterface {

	protected String algorithm;
	protected String key;
	protected HashMap<String, Object> claimAttributes;

	public AbstractTokenClaimBean() {
		super();
		this.claimAttributes = new HashMap<String, Object>();
	}

	public abstract void prepareSecurityPolicy();

	public abstract void setAudience();

	public abstract void setExpirationDate();

	public abstract void setId();

	public abstract void setIssuer();

	public abstract void setNotBefore();

	public abstract void setSubject();

	@Override
	public String getAlgorithm() {
		return this.algorithm;
	}

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public HashMap<String, Object> getClaimAttributes() {
		return this.claimAttributes;
	}

	@Override
	public void setId(final String id) {
		if (id != null) {
			this.claimAttributes.put(TokenType.ID.getTokenType(), id);
		}
	}

	@Override
	public void setSubject(final String subject) {
		if (subject != null) {
			this.claimAttributes.put(TokenType.SUBJECT.getTokenType(), subject);
		}
	}

	@Override
	public void setIssuer(final String issuer) {
		if (issuer != null) {
			this.claimAttributes.put(TokenType.ISSUER.getTokenType(), issuer);
		}
	}

	@Override
	public void setAudience(final String audience) {
		if (audience != null) {
			this.claimAttributes.put(TokenType.AUDIENCE.getTokenType(), audience);
		}
	}

	@Override
	public void setExpirationDate(final Date expirationDate) {
		if (expirationDate != null && expirationDate.compareTo(new Date()) > 0) {
			this.claimAttributes.put(TokenType.EXPIRATION.getTokenType(), expirationDate);
		}
	}

	@Override
	public void setNotBefore(final Date notBefore) {
		if (notBefore != null && notBefore.compareTo(new Date()) > 0) {
			this.claimAttributes.put(TokenType.NOT_BEFORE.getTokenType(), notBefore);
		}
	}

	@Override
	public void setIssuedAt() {
		this.claimAttributes.put(TokenType.ISSUED_AT.getTokenType(), new Date(System.currentTimeMillis()));
	}


	private enum TokenType {
		SUBJECT("sub"), ID("jti"), ISSUER("iss"), AUDIENCE("aud"), EXPIRATION("exp"), NOT_BEFORE("nbf"), ISSUED_AT("iat");
		private final String tokenType;

		private TokenType(final String tokenType) {
			this.tokenType = tokenType;
		}

		public String getTokenType() {
			return tokenType;
		}
	}

}
