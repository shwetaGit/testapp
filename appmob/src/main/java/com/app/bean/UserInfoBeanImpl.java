package com.app.bean;
import java.util.HashMap;

import com.spartan.pluggable.auth.UserBean;

public class UserInfoBeanImpl implements UserBean {
	private HashMap<String, Object> properties;
	private String loginID;
	private boolean isAthenticated;
	private boolean isLocked;

	public UserInfoBeanImpl() {
		super();
	}

	/**
	 * Returns the Error Code
	 * 
	 * @return
	 */
	@Override
	public int errorCode() {
		return 0;
	}

	/**
	 * Error Message
	 * 
	 * @return
	 */
	@Override
	public String errorMessage() {
		return null;
	}

	/**
	 * Returns the Login Id
	 * 
	 * @return String User Login Id
	 */
	@Override
	public String getLoginID() {
		return loginID;
	}

	/**
	 * Returns the Properties of the Account
	 * 
	 * @return HashMap User Defined Properties for the Account based on
	 *         Authentication Type
	 */
	@Override
	public HashMap<String, Object> getProperties() {
		return properties;
	}

	/**
	 * Returns True if the User is Authenticated
	 * 
	 * @return
	 */
	@Override
	public boolean isAthenticated() {
		return isAthenticated;
	}

	/**
	 * Returns True if the user / account is Locked
	 * 
	 * @return
	 */
	@Override
	public boolean isLocked() {
		return isLocked;
	}

	/**
	 * @param properties
	 */
	public void setProperties(final HashMap<String, Object> properties) {
		this.properties = properties;
	}

	/**
	 * @param loginID
	 */
	public void setLoginID(final String loginID) {
		this.loginID = loginID;
	}

	/**
	 * @param isAthenticated
	 */
	public void setAthenticated(final boolean isAthenticated) {
		this.isAthenticated = isAthenticated;
	}

	/**
	 * @param isLocked
	 */
	public void setLocked(final boolean isLocked) {
		this.isLocked = isLocked;
	}

}
