package com.app.config.appSetup.model;
import java.util.List;

import com.athena.config.appsetUp.interfaces.AppConfigurationInterface;
import com.athena.config.appsetUp.interfaces.AuthenticationConfigInterface;
import com.athena.config.appsetUp.interfaces.ConnectorConfigInterface;
import com.athena.config.appsetUp.interfaces.DatabaseConfigInterface;
import com.athena.config.appsetUp.interfaces.DrivePropertiesInterface;
import com.athena.config.appsetUp.interfaces.JwtConfigurationInterface;
import com.athena.config.appsetUp.interfaces.MailConfigInterface;
import com.athena.config.appsetUp.interfaces.PathConfigInterface;
import com.athena.config.appsetUp.interfaces.SchedulerConfigInteface;
import com.athena.config.appsetUp.interfaces.SearchEngineConfigInterface;

public class AppConfiguration implements AppConfigurationInterface {
	private PathConfigInterface pathConfig;
	private AuthenticationConfigInterface authenticationConfig;
	private DatabaseConfigInterface databaseConfig;
	private SearchEngineConfigInterface searchEngineConfig;
	private DrivePropertiesInterface driveProperties;
	private MailConfigInterface mailConfig;
	private SchedulerConfigInteface schedulerConfig;
	private List<ConnectorConfigInterface> connectorConfig;
	private List<JpaProperties> jpaProperties;
	private JwtConfigurationInterface jwtConfig;


	public AppConfiguration() {
		super();
	}

	public AppConfiguration(PathConfigInterface pathConfig, AuthenticationConfigInterface authenticationConfig, DatabaseConfigInterface databaseConfig,
			SearchEngineConfigInterface searchEngineConfig, DrivePropertiesInterface driveProperties, MailConfigInterface mailConfig, SchedulerConfigInteface schedulerConfig,
			List<ConnectorConfigInterface> connectorConfig, List<JpaProperties> jpaProperties , final JwtConfigurationInterface jwtConfig) {
		this.pathConfig = pathConfig;
		this.authenticationConfig = authenticationConfig;
		this.databaseConfig = databaseConfig;
		this.searchEngineConfig = searchEngineConfig;
		this.driveProperties = driveProperties;
		this.mailConfig = mailConfig;
		this.schedulerConfig = schedulerConfig;
		this.connectorConfig = connectorConfig;
		this.jpaProperties = jpaProperties;
		this.jwtConfig = jwtConfig;
	}

	public JwtConfigurationInterface getJwtConfig() {
		return jwtConfig;
	}

	public void setJwtConfig(final JwtConfigurationInterface jwtConfig) {
		this.jwtConfig = jwtConfig;
	}
	
	@Override
	public PathConfigInterface getPathConfig() {
		return pathConfig;
	}

	public void setPathConfig(PathConfigInterface pathConfig) {
		this.pathConfig = pathConfig;
	}

	@Override
	public AuthenticationConfigInterface getAuthenticationConfig() {
		return authenticationConfig;
	}

	public void setAuthenticationConfig(AuthenticationConfigInterface authenticationConfig) {
		this.authenticationConfig = authenticationConfig;
	}

	@Override
	public DatabaseConfigInterface getDatabaseConfig() {
		return databaseConfig;
	}

	public void setDatabaseConfig(DatabaseConfigInterface databaseConfig) {
		this.databaseConfig = databaseConfig;
	}

	@Override
	public SearchEngineConfigInterface getSearchEngineConfig() {
		return searchEngineConfig;
	}

	public void setSearchEngineConfig(SearchEngineConfigInterface searchEngineConfig) {
		this.searchEngineConfig = searchEngineConfig;
	}

	@Override
	public DrivePropertiesInterface getDriveProperties() {
		return driveProperties;
	}

	public void setDriveProperties(DrivePropertiesInterface driveProperties) {
		this.driveProperties = driveProperties;
	}

	@Override
	public MailConfigInterface getMailConfig() {
		return mailConfig;
	}

	public void setMailConfig(MailConfigInterface mailConfig) {
		this.mailConfig = mailConfig;
	}

	@Override
	public SchedulerConfigInteface getSchedulerConfig() {
		return schedulerConfig;
	}

	public void setSchedulerConfig(SchedulerConfigInteface schedulerConfig) {
		this.schedulerConfig = schedulerConfig;
	}

	@Override
	public List<ConnectorConfigInterface> getConnectorConfig() {
		return connectorConfig;
	}

	public void setConnectorConfig(List<ConnectorConfigInterface> connectorConfig) {
		this.connectorConfig = connectorConfig;
	}

	public List<JpaProperties> getJpaProperties() {
		return jpaProperties;
	}

	public void setJpaProperties(List<JpaProperties> jpaProperties) {
		this.jpaProperties = jpaProperties;
	}

	@Override
	public String toString() {
		return "AppConfiguration [pathConfig=" + pathConfig + ", authenticationConfig=" + authenticationConfig + ", databaseConfig=" + databaseConfig + ", searchEngineConfig="
				+ searchEngineConfig + ", driveProperties=" + driveProperties + ", mailConfig=" + mailConfig + ", schedulerConfig=" + schedulerConfig + ", connectorConfig="
				+ connectorConfig + "]";
	}

}
