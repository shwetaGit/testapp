package com.app.config.appSetup.model;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlAttribute;

import com.athena.config.appsetUp.interfaces.AuthPluginInterface;

public class AuthPlugin implements AuthPluginInterface {
	private String authClassName;

	private int authType;

	private int type;

	private HashMap<String, String> authProperties;

	public AuthPlugin(String authClassName, int authType, int type, HashMap<String, String> authProperties) {
		super();
		this.authClassName = authClassName;
		this.authType = authType;
		this.type = type;
		this.authProperties = authProperties;
	}

	@Override
	public String getAuthClassName() {
		return authClassName;
	}

	public void setAuthClassName(String authClassName) {
		this.authClassName = authClassName;
	}

	@Override
	public int getAuthType() {
		return authType;
	}

	public void setAuthType(int authType) {
		this.authType = authType;
	}

	@Override
	@XmlAttribute
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public HashMap<String, String> getAuthProperties() {
		return authProperties;
	}

	public void setAuthProperties(HashMap<String, String> authProperties) {
		this.authProperties = authProperties;
	}

	@Override
	public String toString() {
		return "Plugin [authClassName=" + authClassName + ", authType=" + authType + ", type=" + type + ", authProperties=" + authProperties + "]";
	}
}
