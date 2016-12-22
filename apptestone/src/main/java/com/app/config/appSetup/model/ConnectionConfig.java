package com.app.config.appSetup.model;
import com.athena.config.appsetUp.interfaces.ConnectionConfigInterface;

public final class ConnectionConfig implements ConnectionConfigInterface {

	private final String driverName;
	private final String protocol;
	private final String host;
	private final int port;
	private final String database;
	private final String optionalParams;
	private final String userName;
	private final String password;

	public ConnectionConfig(String driverName, String protocol, String host, int port, String database, String optionalParams, String userName, String password) {
		this.driverName = driverName;
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		this.database = database;
		this.optionalParams = optionalParams;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public String getUrl() {
		StringBuilder url = new StringBuilder();
		url = url.append(this.getProtocol());
		url = url.append(this.getHost());
		url = url.append(":");
		url = url.append(this.getPort());
		url = url.append("/");
		url = url.append(this.getDatabase().trim());
		url = url.append(this.getOptionalParams().trim());
		return url.toString().trim();
	}

	@Override
	public String getDriverName() {
		return driverName;
	}

	@Override
	public String getProtocol() {
		return protocol;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public String getDatabase() {
		return database;
	}

	@Override
	public String getOptionalParams() {
		return optionalParams;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "ConnectionConfig [driverName=" + driverName + ", protocol=" + protocol + ", host=" + host + ", port=" + port + ", database=" + database + ", optionalParams="
				+ optionalParams + ", userName=" + userName + ", password=" + password + "]";
	}
}
