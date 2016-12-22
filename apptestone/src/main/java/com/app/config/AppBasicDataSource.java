package com.app.config;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.apache.commons.dbcp.BasicDataSource;

public class AppBasicDataSource extends BasicDataSource {

	public AppBasicDataSource(final String driverClass, final String url, final String username, final String password) {
		super.setDriverClassName(driverClass);
		super.setUrl(url);
		super.setUsername(username);
		super.setPassword(password);
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	/**
	 * The method is called when undeploying the app for graceful shutdown.
	 */
	public void close() throws SQLException {
		super.close();
	}
}
