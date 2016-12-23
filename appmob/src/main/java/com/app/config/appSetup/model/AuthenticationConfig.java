package com.app.config.appSetup.model;
import com.athena.config.appsetUp.interfaces.AuthPluginInterface;
import com.athena.config.appsetUp.interfaces.AuthenticationConfigInterface;

public class AuthenticationConfig implements AuthenticationConfigInterface {
	private AuthPluginInterface authPlugin;
	private boolean recaptcha;

	public AuthenticationConfig(AuthPluginInterface authPlugin, boolean recaptcha) {
		super();
		this.authPlugin = authPlugin;
		this.recaptcha = recaptcha;
	}

	public AuthenticationConfig() {
		super();
	}

	@Override
	public boolean isRecaptcha() {
		return recaptcha;
	}

	@Override
	public AuthPluginInterface getAuthPlugin() {
		return authPlugin;
	}

	public void setAuthPlugin(AuthPluginInterface authPlugin) {
		this.authPlugin = authPlugin;
	}

	public void setRecaptcha(boolean recaptcha) {
		this.recaptcha = recaptcha;
	}

	@Override
	public String toString() {
		return "AuthenticationConfig [authPlugin=" + authPlugin + ", recaptcha=" + recaptcha + "]";
	}
}
