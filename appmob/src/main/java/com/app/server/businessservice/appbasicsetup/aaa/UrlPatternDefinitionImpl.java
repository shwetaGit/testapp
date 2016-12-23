package com.app.server.businessservice.appbasicsetup.aaa;
import org.springframework.stereotype.Component;

@Component
public class UrlPatternDefinitionImpl implements UrlPatternDefinitions {

	private final String[] aryIgnoreUrl = { "/secure/Authentication/*", "/secure/reportViewController/*", "/secure/queryExecutor/*", "/secure/Logout",
			"/secure/CommonRestServiceImpl/*" };

	/**
	 * Return Public Url Patterns
	 * 
	 * @return
	 */
	@Override
	public String[] getPublicUrlPatterns() {
		return null;
	}

	/**
	 * Return String[] of Filter authorized url Patterns
	 * 
	 * @return
	 */
	@Override
	public String[] getAuthorizedUrlPatterns() {
		return null;
	}

	/**
	 * Return String[] of Filter ignore url Patterns
	 * 
	 * @return
	 */
	@Override
	public String[] getRequestFilterIgnoreUrlPatterns() {
		return aryIgnoreUrl;
	}

	/**
	 * Return Main Page Controller Url Pattern
	 * 
	 * @return
	 */
	@Override
	public String getMainPageControllerUrlPattern() {
		return null;
	}
}
