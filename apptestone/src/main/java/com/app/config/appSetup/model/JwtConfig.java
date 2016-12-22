package com.app.config.appSetup.model;
import java.sql.Timestamp;

import com.athena.config.appsetUp.interfaces.JwtConfigurationInterface;


public class JwtConfig  implements JwtConfigurationInterface{

    private String jwtAlgorithm;

    private Timestamp expiration;

    private String tokenKey;

    public JwtConfig(final String jwtAlgo , final Timestamp expiration, final String tokenKey) {
    	this.jwtAlgorithm = jwtAlgo;
    	this.expiration = expiration;
    	this.tokenKey = tokenKey;
	}

    @Override
	public String getJwtAlgorithm() {
        return jwtAlgorithm;
    }

    @Override
	public void setJwtAlgorithm(final String jwtAlgorithm) {
        if (jwtAlgorithm != null) {
            this.jwtAlgorithm = jwtAlgorithm;
        }
    }

    @Override
	public Timestamp getExpiration() {
        return expiration == null ? expiration : new Timestamp(expiration.getTime());
    }

    @Override
	public void setExpiration(final Timestamp expiration) {
        if (expiration != null) {
            this.expiration = new Timestamp(expiration.getTime());
        }
    }

    @Override
	public String getTokenKey() {
        return tokenKey;
    }

    @Override
	public void setTokenKey(final String tokenKey) {
        if (tokenKey != null) {
            this.tokenKey = tokenKey;
        }
    }
}
