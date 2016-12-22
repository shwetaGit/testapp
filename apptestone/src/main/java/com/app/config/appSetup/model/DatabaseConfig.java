package com.app.config.appSetup.model;
import com.athena.config.appsetUp.interfaces.ConnectionConfigInterface;
import com.athena.config.appsetUp.interfaces.ConnectionPoolingInterface;
import com.athena.config.appsetUp.interfaces.DatabaseConfigInterface;

public final class DatabaseConfig implements DatabaseConfigInterface {
	private final ConnectionConfigInterface connectionConfig;
	private final ConnectionPoolingInterface connectionPooling;

	public DatabaseConfig(ConnectionConfigInterface connectionConfig, ConnectionPoolingInterface connectionPooling) {
		this.connectionConfig = connectionConfig;
		this.connectionPooling = connectionPooling;
	}

	@Override
	public ConnectionConfigInterface getConnectionConfig() {
		return connectionConfig;
	}

	@Override
	public ConnectionPoolingInterface getConnectionPooling() {
		return connectionPooling;
	}

	@Override
	public String toString() {
		return "DatabaseConfig [connectionConfig=" + connectionConfig + ", connectionPooling=" + connectionPooling + "]";
	}
}
