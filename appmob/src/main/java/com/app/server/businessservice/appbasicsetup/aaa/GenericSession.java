package com.app.server.businessservice.appbasicsetup.aaa;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.spartan.server.interfaces.LoginSessionDataRepository;
import com.spartan.server.interfaces.LoginSessionInterface;
import com.spartan.server.interfaces.LoginSessionRepositoryInterface;
import com.spartan.server.interfaces.SessionDataInterface;

public abstract class GenericSession {

	private HttpSession session;

	@Autowired
	private LoginSessionRepositoryInterface loginInterfaceRepository;
	
	
	@Autowired
	private LoginSessionDataRepository loginSessionDataRepository;
	

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public HttpSession setSessionAttribute(String key, Object value){
		session.setAttribute(key, value);
		
		return session;
		
	}

	public HttpSession setSessionClientIp(Object value) {
		session.setAttribute(Attributes.CLIENT_IP.getName(), value);
		return session;
	}

	public HttpSession setSessionClientPort(Object value) {
		session.setAttribute(Attributes.CLIENT_PORT.getName(), value);
		return session;
	}

	public HttpSession setSessionLoginId(Object value) {
		session.setAttribute(Attributes.LOGIN_ID.getName(), value);
		return session;
	}

	public HttpSession setSessionUserId(Object value) {
		session.setAttribute(Attributes.USER_ID.getName(), value);
		return session;
	}

	public HttpSession setSessionTimeZone(Object value) {
		session.setAttribute(Attributes.TIME_ZONE.getName(), value);
		return session;
	}

	public HttpSession setSessionTimeZoneId(Object value) {
		session.setAttribute(Attributes.TIME_ZONE_ID.getName(), value);
		return session;
	}

	public HttpSession setSessionTimeout(Object value) {
		session.setAttribute(Attributes.SESSION_TIME_OUT.getName(), value);
		return session;
	}

	public HttpSession setSessionContactId(Object value) {
		session.setAttribute(Attributes.CONTACT_ID.getName(), value);
		return session;
	}

	public HttpSession setSessionUserAccessCode(Object value) {
		session.setAttribute(Attributes.USER_ACCESS_CODE.getName(), value);
		return session;
	}

	public HttpSession setSessionQKeHash(Object value) {
		session.setAttribute(Attributes.QKE_HASH.getName(), value);
		return session;
	}

	public HttpSession setSessionUsidHash(Object value) {
		session.setAttribute(Attributes.USID_HASH.getName(), value);
		return session;
	}
	
	
	public void saveLoginSession(LoginSessionInterface loginSessionInterface) throws Exception {

		loginInterfaceRepository.saveSession(loginSessionInterface);

	}
	
	public void saveSessionData(SessionDataInterface SessionDataInterface) throws Exception{
		
		loginSessionDataRepository.saveSessionData(SessionDataInterface);
	}


}
