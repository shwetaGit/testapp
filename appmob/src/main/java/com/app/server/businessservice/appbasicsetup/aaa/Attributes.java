package com.app.server.businessservice.appbasicsetup.aaa;
public enum Attributes {
	
	CLIENT_IP("clientIP"), 
	CLIENT_PORT("clientPort"),
	LOGIN_ID("loginId"), 
	USER_ID("userId"),
	TIME_ZONE("timeZone"),
	TIME_ZONE_ID("timeZoneId"),
	SESSION_TIME_OUT("sessionTimeout"), 
	CONTACT_ID("contactId"), 
	USER_ACCESS_CODE("userAccessCode"), 
	QKE_HASH("qKeHash"),
	USID_HASH("usidHash");
	
	private final String name;


private Attributes(String name)
{
    this.name = name;
}

public String getName()
{
    return ""+name;
}

}





