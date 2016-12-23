package com.app.server.businessservice.appbasicsetup.aaa;
public class Payload {

	String usidHash;
	String loginId;
	Integer userAccessCode;
	String contactId;
	String timeZoneId;
	String timeZone;
	String cookieTokenName;
	String userId;
	String qkeyHash;
	String clientIp;
	Integer clientPort;
	Integer sessionTimeOut;

	public Payload(String usidHash, String loginId, Integer userAccessCode, String contactId, String timeZoneId, String timeZone, String cookieTokenName, String userId,
			String qkeyHash, String clientIp, Integer clientPort, Integer sessionTimeOut) {
		super();
		this.usidHash = usidHash;
		this.loginId = loginId;
		this.userAccessCode = userAccessCode;
		this.contactId = contactId;
		this.timeZoneId = timeZoneId;
		this.timeZone = timeZone;
		this.cookieTokenName = cookieTokenName;
		this.userId = userId;
		this.qkeyHash = qkeyHash;
		this.clientIp = clientIp;
		this.clientPort = clientPort;
		this.sessionTimeOut = sessionTimeOut;
	}

	public String getUsidHash() {
		return usidHash;
	}

	public String getLoginId() {
		return loginId;
	}

	public Integer getUserAccessCode() {
		return userAccessCode;
	}

	public String getContactId() {
		return contactId;
	}

	public String getTimeZoneId() {
		return timeZoneId;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public String getCookieTokenName() {
		return cookieTokenName;
	}

	public String getUserId() {
		return userId;
	}

	public String getQkeyHash() {
		return qkeyHash;
	}

	public String getClientIp() {
		return clientIp;
	}

	public Integer getClientPort() {
		return clientPort;
	}

	public Integer getSessionTimeOut() {
		return sessionTimeOut;
	}

}
