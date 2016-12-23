package com.app.server.businessservice.appbasicsetup.aaa;
public interface UserAccessRightService {

	/**
	 * Setting user Data Access in session
	 * 
	 * @throws Exception
	 */
	void addUserAccessDatainSession() throws Exception;

	/**
	 * Return UserData Access Query
	 * 
	 * @return
	 * @throws Exception
	 */
	String getUserAccessData() throws Exception;

}
