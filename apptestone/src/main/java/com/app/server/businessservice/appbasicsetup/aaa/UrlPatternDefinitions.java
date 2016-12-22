package com.app.server.businessservice.appbasicsetup.aaa;
public interface UrlPatternDefinitions {
	/**
	 * Return Public Url Patterns
	 * 
	 * @return
	 */
	String[] getPublicUrlPatterns();

	/**
	 * Return String[] of Filter authorized url Patterns
	 * 
	 * @return
	 */
	String[] getAuthorizedUrlPatterns();

	/**
	 * Return String[] of Filter ignore url Patterns
	 * 
	 * @return
	 */
	String[] getRequestFilterIgnoreUrlPatterns();

	/**
	 * Return Main Page Controller Url Pattern
	 * 
	 * @return
	 */
	String getMainPageControllerUrlPattern();

}
