package com.app.config.appSetup.xmlParser;
import com.app.config.appSetup.model.AppConfiguration;

import com.app.config.appSetup.model.AuthPlugin;

import com.app.config.appSetup.model.AuthenticationConfig;

import com.app.config.appSetup.model.ConnectionConfig;

import com.app.config.appSetup.model.ConnectionPooling;

import com.app.config.appSetup.model.ConnectorConfig;

import com.app.config.appSetup.model.DatabaseConfig;

import com.app.config.appSetup.model.DriveProperties;

import com.app.config.appSetup.model.MailConfig;

import com.app.config.appSetup.model.PathConfig;

import com.app.config.appSetup.model.SchedulerConfig;

import com.app.config.appSetup.model.SearchEngineConfig;

import com.app.config.appSetup.model.JpaProperties;

import com.app.config.appSetup.model.JwtConfig;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.athena.server.pluggable.utils.Ciphering;
import com.athena.config.appsetUp.interfaces.AppConfigurationInterface;
import com.athena.config.appsetUp.interfaces.AuthenticationConfigInterface;
import com.athena.config.appsetUp.interfaces.ConnectionConfigInterface;
import com.athena.config.appsetUp.interfaces.ConnectionPoolingInterface;
import com.athena.config.appsetUp.interfaces.ConnectorConfigInterface;
import com.athena.config.appsetUp.interfaces.DatabaseConfigInterface;
import com.athena.config.appsetUp.interfaces.DrivePropertiesInterface;
import com.athena.config.appsetUp.interfaces.JwtConfigurationInterface;
import com.athena.config.appsetUp.interfaces.MailConfigInterface;
import com.athena.config.appsetUp.interfaces.SchedulerConfigInteface;
import com.athena.config.appsetUp.interfaces.SearchEngineConfigInterface;

public class AppSetupSAXParser extends AbstractSAXParser {
	// APP SETUP PROPERTIES
	private AppConfigurationInterface appConfiguration;
	private PathConfig pathConfig;

	private String basePath;
	private String webAppPath;
	private String os;

	// AUTHENTICATION
	private AuthenticationConfigInterface authenticationConfig;
	private AuthPlugin authPlugin;
	private String authClassName;
	private int authType;
	private int type;
	private String key;
	private final HashMap<String, String> authProperties = new HashMap<String, String>();
	private boolean recaptcha;

	// DATABASE CONFIG PROPERTIES
	private DatabaseConfigInterface databaseConfig;
	// CONNECTION CONFIG PROPERTIES
	private ConnectionConfigInterface connectionConfig;
	private String driverName;
	private String protocol;
	private String host;
	private int port;
	private String database;
	private String optionalParams;
	private String userName;
	private String password;
	// CONNECTION POOLING PROPERTIES
	private ConnectionPoolingInterface connectionPooling;
	private int initialSize;
	private int maxActive;
	private int maxIdle;
	private int minIdle;
	private boolean testOnBorrow;
	private boolean testOnReturn;
	private boolean testWhileIdle;
	private String validationQuery;
	private int timeBetweenEvictionRunsMillis;
	private int numTestPerEvictionRun;

	// DRIVE PROPERTIES
	private DrivePropertiesInterface driveProperties;
	private String drive;
	private String publicDrive;
	private String privateDrive;

	// APP PROPERTIES
	private SearchEngineConfigInterface searchEngineConfig;
	private String docResultFields;
	private String language;
	private String searchAppURL;
	private String searchbasePath;

	// MAIL CONFIGURATION PROPERTIES
	private MailConfigInterface mailConfig;
	private int smtpPort;
	private int smtpsPort;
	private boolean smtpAuth;
	private boolean smtpTls;
	private boolean smtpSsl;

	// SCHEDULER CONFIGURATION
	private String scheudlerurl;
	private int schedulerRefreshTime;
	private SchedulerConfigInteface schedulerConfig;

	// Connector Config Property

	private String serverId;
	private String serverName;
	private String ipAddress;
	private String portNo;
	private String databaseName;
	// private String userName;
	// private String password;
	private Integer connectorType;
	private String filePath;
	private String urlConfig;
	// private String protocol;
	// database type and database Driver name added for external integration
	private String databaseType;
	private String databaseDriverName;
	private String databaseUrl;

	//Jwt configuration
	private String algorithm;
	private Timestamp expiration;
	private String tokenKey;
	private JwtConfigurationInterface jwtConfig;


	List<ConnectorConfigInterface> connectionConfigList = new ArrayList<ConnectorConfigInterface>();

	private final List<JpaProperties> listOfJpaProperties = new ArrayList<JpaProperties>();
	private String jpaPropertyName, jpaPropertyValue;

	public AppSetupSAXParser() {
	}

	@Override
	public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
		resetTagData();
		if (qName.equalsIgnoreCase("pathConfig")) {
			os = attributes.getValue("os");
		}
		if (qName.equalsIgnoreCase("authPlugin")) {
			type = Integer.valueOf(attributes.getValue("type"));
		}
		if (qName.equalsIgnoreCase("property")) {
			jpaPropertyName = attributes.getValue("name");
			jpaPropertyValue = attributes.getValue("value");
		}
	}

	@Override
	public void endElement(final String uri, final String localName, final String qName) throws SAXException {
		final String dataValue = getTagData();

		switch (qName) {
		case "baseFilePath": // database config properties
			basePath = dataValue.replaceAll("(\r\n|\n|\t)", "");
			break;
		case "webAppPath":
			webAppPath = dataValue.replaceAll("(\r\n|\n|\t)", "");
			break;
		case "pathConfig":
			pathConfig = new PathConfig(basePath, webAppPath);
			break;
		case "authType":
			authType = Integer.valueOf(dataValue);
			break;
		case "authClassName":
			authClassName = dataValue;
			break;
		case "key":
			key = dataValue;
			break;
		case "value":
			authProperties.put(key, dataValue);
			break;
		case "recaptcha":
			recaptcha = Boolean.valueOf(dataValue);
			break;
		case "authPlugin":
			authPlugin = new AuthPlugin(authClassName, authType, type, authProperties);
			break;
		case "authenticationConfig":
			authenticationConfig = new AuthenticationConfig(authPlugin, recaptcha);
			break;
		case "driverName":
			driverName = dataValue;
			break;
		case "protocol":
			protocol = dataValue.replaceAll("(\r\n|\n|\t)", "");
			break;
		case "host":
			host = dataValue;
			break;
		case "port":
			port = Integer.parseInt(dataValue);
			break;
		case "database":
			database = dataValue;
			break;
		case "optionalParams":
			optionalParams = dataValue.replaceAll("(\r\n|\n|\t)", "");
			break;
		case "userName":
			userName = dataValue;
			break;
		case "password":
			try {
				password = new Ciphering().deCipher(dataValue);
			} catch (final IOException e) {
				e.printStackTrace();
			}
			break;
		case "initialSize": // database pooling properties
			initialSize = Integer.parseInt(dataValue);
			break;
		case "maxActive":
			maxActive = Integer.parseInt(dataValue);
			break;
		case "maxIdle":
			maxIdle = Integer.parseInt(dataValue);
			break;
		case "minIdle":
			minIdle = Integer.parseInt(dataValue);
			break;
		case "testOnBorrow":
			testOnBorrow = Boolean.parseBoolean(dataValue);
			break;
		case "testOnReturn":
			testOnReturn = Boolean.parseBoolean(dataValue);
			break;
		case "testWhileIdle":
			testWhileIdle = Boolean.parseBoolean(dataValue);
			break;
		case "validationQuery":
			validationQuery = dataValue;
			break;
		case "timeBetweenEvictionRunsMillis":
			timeBetweenEvictionRunsMillis = Integer.parseInt(dataValue);
			break;
		case "numTestPerEvictionRun":
			numTestPerEvictionRun = Integer.parseInt(dataValue);
			break;
		case "drive": // reading drive properties
			drive = dataValue;
			break;
		case "drivePublic":
			publicDrive = dataValue;
			break;
		case "driveShared":
			privateDrive = dataValue;
			break;
		case "solrHome":
			break;
		case "docResultFields":
			docResultFields = dataValue;
			break;
		case "language":
			language = dataValue;
			break;
		case "searchAppURL":
			searchAppURL = dataValue;
			break;
		case "basePath":
			searchbasePath = dataValue;
			break;
		// MAIL CONFIGURATION PROPERTIES
		case "smtpHost":
			host = dataValue;
			break;
		case "smtpPort":
			smtpPort = Integer.parseInt(dataValue);
			break;
		case "smtpsPort":
			smtpsPort = Integer.parseInt(dataValue);
			break;
		case "smtpAuth":
			smtpAuth = Boolean.parseBoolean(dataValue);
			break;
		case "smtpStarttlsEnable":
			smtpTls = Boolean.parseBoolean(dataValue);
			break;
		case "smtpSslEnable":
			smtpSsl = Boolean.parseBoolean(dataValue);
			break;
		case "schedulerurl":
			scheudlerurl = dataValue;
			break;
		case "schedulerRefreshTime":
			schedulerRefreshTime = Integer.parseInt(dataValue);
			break;
		case "serverId":
			serverId = dataValue;
			break;

		case "serverName":
			serverName = dataValue;
			break;
		case "ipAddress":
			ipAddress = dataValue;
			break;
		case "portNo":
			portNo = dataValue;
			break;
		case "databaseName":
			databaseName = dataValue;
			break;
		case "filePath":
			filePath = dataValue;
			break;
		case "urlConfig":
			urlConfig = dataValue;
			break;
		case "databaseType":
			databaseType = dataValue;
			break;
		case "databaseDriverName":
			databaseDriverName = dataValue;
			break;
		case "databaseUrl":
			databaseUrl = dataValue;
			break;
		case "webServer.BasePath":
			break;
		case "connectorType":
			connectorType = Integer.parseInt(dataValue);
			break;
			
		case "algorithm":
			algorithm = dataValue;
			break;
			
		case "expiration":
			expiration = Timestamp.valueOf(dataValue);
			break;
			
		case "tokenkey"	:
			tokenKey = dataValue;
			break;
			
		case "property": // creation of objects
			listOfJpaProperties.add(new JpaProperties(jpaPropertyName, jpaPropertyValue));
			break;
		case "connectionConfig": // creation of objects
			connectionConfig = new ConnectionConfig(driverName, protocol, host, port, database, optionalParams, userName, password);
			break;
		case "connectionPooling":
			connectionPooling = new ConnectionPooling(initialSize, maxActive, maxIdle, minIdle, testOnBorrow, testOnReturn, testWhileIdle, validationQuery,
					timeBetweenEvictionRunsMillis, numTestPerEvictionRun);
			break;
		case "databaseConfig":
			databaseConfig = new DatabaseConfig(connectionConfig, connectionPooling);
			break;
		case "cloudDriveConfig":
			driveProperties = new DriveProperties(drive, publicDrive, privateDrive);
			break;
		case "searchEngineConfig":
			searchEngineConfig = new SearchEngineConfig(docResultFields, language, searchAppURL, searchbasePath);
			break;
		case "mailConfig":
			mailConfig = new MailConfig(userName, password, smtpAuth, host, smtpPort, smtpTls, smtpSsl, smtpsPort);
			break;
		case "schedulerConfig":
			schedulerConfig = new SchedulerConfig(scheudlerurl, schedulerRefreshTime);
			break;
		case "connectorConfig":
			final ConnectorConfig connectorConfig = new ConnectorConfig(serverId, serverName, ipAddress, portNo, databaseName, userName, password, connectorType, protocol, filePath,
					urlConfig, databaseType, databaseDriverName, databaseUrl);
			connectionConfigList.add(connectorConfig);
			break;
		
		case "JWTConfig":	
				jwtConfig = new JwtConfig(algorithm , expiration , tokenKey);
				break;
			
		case "AppConfig":
			appConfiguration = new AppConfiguration(pathConfig, authenticationConfig, databaseConfig, searchEngineConfig, driveProperties, mailConfig, schedulerConfig,
					connectionConfigList, listOfJpaProperties, jwtConfig);
			break;
		default:
			break;
		}
	}

	@Override
	public Object getObject() {
		return appConfiguration;
	}
}
