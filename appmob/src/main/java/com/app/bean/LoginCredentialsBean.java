package com.app.bean;
import com.spartan.pluggable.auth.LoginCredentials;

public class LoginCredentialsBean implements LoginCredentials {

	private String loginId;
	private String password;
	private String latitude;
	private String longitude;

	public LoginCredentialsBean(final String loginId, final String password, final String latitude, final String longitude) {
		super();
		this.loginId = loginId;
		this.password = password;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public LoginCredentialsBean() {
		super();
	}

	/**
	 * Returns the Login ID
	 * 
	 * @return
	 */
	@Override
	public String getLoginId() {
		return loginId;
	}

	/**
	 * Returns the Password
	 * 
	 * @return
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * Returns the Latitude
	 * 
	 * @return
	 */
	@Override
	public String getLatitude() {
		return latitude;
	}

	/**
	 * Returns the Longitude
	 * 
	 * @return
	 */
	@Override
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param loginId
	 */
	public void setLoginId(final String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @param password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * @param latitude
	 */
	public void setLatitude(final String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude
	 */
	public void setLongitude(final String longitude) {
		this.longitude = longitude;
	}

	/**
	 * Returns the hashCode
	 * 
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loginId == null) ? 0 : loginId.hashCode());
		return result;
	}

	/**
	 * Returns true/false on the basis of loginId
	 * 
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		LoginCredentialsBean other = (LoginCredentialsBean) obj;
		if (loginId == null) {
			if (other.loginId != null) {
				return false;
			}
		} else if (!loginId.equals(other.loginId)) {
			return false;
		}
		return true;
	}

}
