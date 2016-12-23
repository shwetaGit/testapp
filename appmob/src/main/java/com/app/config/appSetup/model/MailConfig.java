package com.app.config.appSetup.model;
import com.athena.config.appsetUp.interfaces.MailConfigInterface;

public final class MailConfig implements MailConfigInterface {
	private final String username;
	private final String password;
	private final boolean smtpAuth;
	private final String host;
	private final int smtpPort;
	private final int smtpsPort;
	private final boolean smtpTls;
	private final boolean smtpSsl;

	public MailConfig(String username, String password, boolean smtpAuth, String host, int port, boolean smtpTls, boolean smtpSsl, int smtpsPort) {
		this.username = username;
		this.password = password;
		this.smtpAuth = smtpAuth;
		this.host = host;
		this.smtpPort = port;
		this.smtpTls = smtpTls;
		this.smtpSsl = smtpSsl;
		this.smtpsPort = smtpsPort;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isSmtpAuth() {
		return smtpAuth;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public int getSmtpPort() {
		return smtpPort;
	}

	@Override
	public boolean isSmtpTls() {
		return smtpTls;
	}

	@Override
	public boolean isSmtpSsl() {
		return smtpSsl;
	}

	@Override
	public int getSmtpsPort() {
		return smtpsPort;
	}

	@Override
	public String toString() {
		return "MailConfig [username=" + username + ", password=" + password + ", smtpAuth=" + smtpAuth + ", host=" + host + ", port=" + smtpPort + ", smtpsPort=" + smtpsPort
				+ ", smtpTls=" + smtpTls + ", smtpSsl=" + smtpSsl + "]";
	}
}
