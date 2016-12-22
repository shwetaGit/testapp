package com.app.config;
import com.app.server.businessservice.appbasicsetup.aaa.ADAuthBusinessService;

import com.app.server.businessservice.appbasicsetup.aaa.DBAuthBusinessService;

import com.app.config.appSetup.model.AppConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.spartan.pluggable.auth.PluggableAuthConnector;

@Configuration
public class SecurityConfig {
	/** plugin type internal */
	private final int PLUGIN_TYPE_INTERNAL_AUTH = 0;

	/** plugin type external. This will user defined external jar's class */
	private final int PLUGIN_TYPE_EXTERNAL_AUTH = 1;

	/** auth type database */
	private final int AUTH_TYPE_DB = 1;
	/** auth type active directory */
	private final int AUTH_TYPE_AD = 2;

	@Autowired
	private AppConfiguration appConfig;

	/**
	 * Bean to create Pluggable authentication object. Based on plugin type aand
	 * authentication type authentication object is created. Plugin type
	 * external will user defined external jar's class and Authentication type
	 * decides the type of authentication. 1 = Internal (JDBC) and 2 = LDAP
	 * */
	@Bean(name = "userAuthenticator")
	public PluggableAuthConnector userAuthenticator() throws Exception {
		PluggableAuthConnector authentication = null;
		if (Integer.valueOf(appConfig.getAuthenticationConfig().getAuthPlugin().getType()) == PLUGIN_TYPE_INTERNAL_AUTH) {
			if (Integer.valueOf(appConfig.getAuthenticationConfig().getAuthPlugin().getAuthType()) == AUTH_TYPE_DB) {
				authentication = new DBAuthBusinessService();
			} else if (Integer.valueOf(appConfig.getAuthenticationConfig().getAuthPlugin().getAuthType()) == AUTH_TYPE_AD) {
				/* External (LDAP) */
				authentication = new ADAuthBusinessService(appConfig.getAuthenticationConfig().getAuthPlugin().getAuthProperties());
			} else {
				throw new Exception("Plugin type 0: Authentication type not set properly");
			}
		} else if (Integer.valueOf(appConfig.getAuthenticationConfig().getAuthPlugin().getType()) == PLUGIN_TYPE_EXTERNAL_AUTH) {
			try {
				authentication = (PluggableAuthConnector) Class.forName(appConfig.getAuthenticationConfig().getAuthPlugin().getAuthClassName()).newInstance();
			} catch (ClassNotFoundException e) {
				throw new ClassNotFoundException("Plugin type 1: Auth class could not be loaded.", e);
			}

		} else {
			throw new Exception("Plugin type 1: Auth class " + appConfig.getAuthenticationConfig().getAuthPlugin().getAuthClassName() + " not found.");
		}
		return authentication;
	}
}
