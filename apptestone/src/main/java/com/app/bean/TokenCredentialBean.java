package com.app.bean;
import com.spartan.pluggable.auth.TokenCredential;

public class TokenCredentialBean implements TokenCredential {
	private String password;
	private String appSessionId;

	public TokenCredentialBean() {
		super();
	}

	public TokenCredentialBean(final String password, final String appSessionId) {
		super();
		this.password = password;
		this.appSessionId = appSessionId;
	}

	/**
	 * Returns the Token based Credentials
	 * 
	 * This will be used for Authenticating the User Identity.
	 * 
	 * @return
	 */
	@Override
	public String getTokenCredentials() {
		return password;
	}

	/**
	 * Returns the App Token ID for a valid session
	 * 
	 * @return
	 */
	@Override
	public String getAppToken() {
		return appSessionId;
	}

	/**
	 * @param password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * @param appSessionId
	 */
	public void setAppSessionId(final String appSessionId) {
		this.appSessionId = appSessionId;
	}

}
