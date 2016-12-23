package com.app.server.businessservice.appbasicsetup.aaa;
import com.app.util.IpAddress;

import com.app.shared.appbasicsetup.usermanagement.LoginSession;

import com.app.shared.appbasicsetup.usermanagement.SessionData;

import com.app.server.businessservice.appbasicsetup.aaa.Attributes;

import com.app.server.businessservice.appbasicsetup.aaa.CookieValidation;

import com.app.server.businessservice.appbasicsetup.aaa.DataType;

import com.app.server.businessservice.appbasicsetup.aaa.UserAccessRightService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.athena.server.pluggable.utils.HashAlgorithms;
import com.athena.server.pluggable.utils.helper.JSONObjectMapperHelper;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.pluggable.auth.UserBean;
import com.spartan.pluggable.exception.auth.AccessDeniedException;
import com.spartan.pluggable.exception.layers.db.PersistenceException;
import com.spartan.server.authenticate.repository.AuthenticateRepository;
import com.spartan.server.interfaces.LoginSessionDataRepository;
import com.spartan.server.interfaces.LoginSessionInterface;
import com.spartan.server.interfaces.LoginSessionRepositoryInterface;
import com.spartan.server.interfaces.SessionDataInterface;
import com.spartan.server.interfaces.UserAuthentication;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionDataGeneration extends GenericSession {

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@Autowired
	private AuthenticateRepository authenticateRepository;
	
	@Autowired
	private LoginSessionDataRepository loginSessionDataRepository;
	
	@Autowired
	private LoginSessionRepositoryInterface loginInterfaceRepository;
	
	@Autowired
	private CookieValidation cookieValidation;
	
	
	@Autowired
	private UserAccessRightService userAccessRightService;
	
	private HashMap<String, Object>  userAttributes;
	
	private HttpServletRequest request;
	
	private HttpSession session;
	
	
	
	
	public HashMap<String, Object> getUserAttributes() {
		return userAttributes;
	}


	public void setUserAttributes(HashMap<String, Object> userAttributes) {
		this.userAttributes = userAttributes;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public HttpSession getSession() {
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
	}


	//private String usidKey = "NOKEY";
	

	/**
	 * setting some required values in session
	 * 
	 * @param session
	 * @param request
	 * @param userAttributes
	 */
//	public void setSessionData(HttpSession session) {
//
//		int userSessionTimeout = Integer.parseInt(userAttributes.get("sessionTimeout").toString());
//		String usidKey = "NOKEY";
//		usidKey = generateUsidKey(session.getId(), userAttributes.get("loginId").toString(), userAttributes.get("credentials").toString(), request);
//		session.setMaxInactiveInterval(userSessionTimeout);
//		setSession(session); 
//		String userHash = generateUserHash(usidKey);
//		saveUserDataToSession(session, userHash, userAttributes, usidKey);
////		setSessionAttributes(session, "userId", attributes.get("userId"));//ALGO0001-Delete
//		setSessionUserId(userAttributes.get("userId")); //ALGO0001-Insert
//		
//	}
	
	public void setSession(){
		super.setSession(session); 
	}	
	public void setSessionClientIp(){
		super.setSessionClientIp(request.getRemoteHost());
	}
	
	public void setSessionClientPort(){
		super.setSessionClientPort(request.getRemotePort());
	}
	
	
	public void setSessionLoginId()
	{
		super.setSessionLoginId(userAttributes.get(Attributes.LOGIN_ID.getName()));
	}
	
	public void setSessionUserId(){
		super.setSessionUserId(userAttributes.get(Attributes.USER_ID.getName())); 
	}
	
	public void setSessionTimeZone(){
		super.setSessionTimeZone(userAttributes.get(Attributes.TIME_ZONE.getName())); 
	}
	
	public void setSessionTimeZoneId(){
		super.setSessionTimeZoneId(userAttributes.get(Attributes.TIME_ZONE_ID.getName())); 
	}
	
	public void setSessionTimeout(){
		super.setSessionTimeout(userAttributes.get(Attributes.SESSION_TIME_OUT.getName())); 
	}
	
	public void setSessionContactId(){
		super.setSessionContactId(userAttributes.get(Attributes.CONTACT_ID.getName())); 
	}
	
	public void setSessionUserAccessCode(){
		super.setSessionUserAccessCode(userAttributes.get(Attributes.USER_ACCESS_CODE.getName())); 
	}
	
	public void setSessionQKeHash(){
		String usidKey = "NOKEY";
		usidKey = generateUsidKey(session.getId(), userAttributes.get("loginId").toString(), userAttributes.get("credentials").toString(), request);
		super.setSessionQKeHash(generateQKeHash(userAttributes.get("credentials").toString(), usidKey)); 
	}
	
	
	
	

	private String generatePasswordHash(String userCredentials) {
		/** Store User specific info into session */

		String pswdHash = "";
		try {
			pswdHash = HashAlgorithms.getInstance().computeHash(userCredentials, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pswdHash;
	}

	public String generateUsidKey(final String sessionId, final String loginId, final String userCredentials, final HttpServletRequest request) {
		Date date = new Date();
		StringBuilder sb = new StringBuilder();
		sb.append(sessionId).append("|");
		sb.append(loginId).append("|");
		sb.append(request.getRemoteHost()).append("|");
		sb.append(request.getRemotePort()).append("|");
		sb.append(date.getTime()).append("|");
		sb.append(generatePasswordHash(userCredentials)).append("|");
		return sb.toString();// Plain User Session ID
	}

	public String generateUserHash(final String usidKey) {

		String userHash = "";
		try {
			userHash = HashAlgorithms.getInstance().computeHash(usidKey, HashAlgorithms.SHA_256);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userHash;
	}

	/**
	 * Return QKeHash from session inputs
	 * 
	 * @return
	 */
	public String generateQKeHash(final String userCredentials, final String usidKey) {
		// Create Unique Application Session ID for the Cookie
		String qKeyHash = "";
		String pswdHash = "";
		// Create the Session IDs (User and Cookie) after the successful
		// validation.
		String qKe = "";

		try {
//			pswdHash = HashAlgorithms.getInstance().computeHash(userCredentials, 1);

			qKe = usidKey;// Plain Cookie Session ID

			try {
				qKeyHash = HashAlgorithms.getInstance().computeHash(qKe, HashAlgorithms.SHA_256);
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new AccessDeniedException("Unable to compute Cookie Session ID QKe using Key 2", "ABSAA154900400", new AccessDeniedException());
			}

		} catch (Exception ignored) {
			ignored.printStackTrace();
		}

		return qKeyHash;
	}

	/**public void saveUserDataToSession(HttpSession session, String userHash, HashMap<String, Object> attributes, String usidkey) {
		try {

			UserAuthentication userAuth = authenticateRepository.getUserByLoginId(attributes.get("loginId").toString());
			if (userAuth != null) {
				userAuth.setContainerSessionId(session.getId());
				userAuth.setUserHash(userHash); // Set the User Hash in the
				userAuth.setQKeHash(session.getAttribute("qKeHash").toString());
//				setSessionAttributes(session, "usidHash", userHash);
				setSessionUsidHash(session.getAttribute("qKeHash").toString());
//				setSessionAttribute(Attributes.USID_HASH.getName(), userHash);
			///TO DO 
				session.setAttribute(userHash, userAuth.toJsonString());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}**/
	
	
	public void saveUserDataToSession(HttpSession session, HashMap<String, Object> attributes) {
		try {

			UserAuthentication userAuth = authenticateRepository.getUserByLoginId(attributes.get("loginId").toString());
			if (userAuth != null) {
				userAuth.setContainerSessionId(session.getId());
				userAuth.setUserHash(session.getAttribute("qKeHash").toString()); // Set the User Hash in the
				userAuth.setQKeHash(session.getAttribute("qKeHash").toString());
				setSessionAttribute(Attributes.USID_HASH.getName(), session.getAttribute("qKeHash").toString());
			///TO DO 
				session.setAttribute(session.getAttribute("qKeHash").toString(), userAuth.toJsonString());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Saving data of logged in user in login entity
	 *
	 * @param session
	 * @param request
	 * @param userBean
	 * @throws Exception
	 */
	public void saveSessionData(final HttpSession session, final HttpServletRequest request, final UserBean userBean) throws Exception {
		final long currentTime = System.currentTimeMillis();

		final Enumeration<String> sessionNamesEnumeration = session.getAttributeNames();

		final List<String> sessionattributeNames = new ArrayList<String>();

		while (sessionNamesEnumeration.hasMoreElements()) {
			sessionattributeNames.add(sessionNamesEnumeration.nextElement());
		}

		LoginSessionInterface loginSession = null;
		try {
			loginSession = loginInterfaceRepository.findById(session.getId());
		} catch (final PersistenceException e1) {
			System.err.println("Requested Session Id not found");
		} catch (final Exception e1) {
			System.err.println("Requested Session Id not found");
		}
		if (loginSession != null) {
			loginSession.setLoginTime(new Timestamp(currentTime));
			loginSession.setLogoutTime(null);
			try {
				loginInterfaceRepository.updateSession(loginSession);
			} catch (final PersistenceException e) {
				e.printStackTrace();
			}
			
		} else {
			final IpAddress ipaddress = new IpAddress(request.getRemoteHost());
			try {
				LoginSessionInterface loginSessionInterface = new LoginSession(session.getAttribute("usidHash").toString(),
												userBean.getProperties().get("userId").toString(), null, session.getId().toString(),
												new Timestamp(currentTime), new Timestamp(currentTime), new Timestamp(currentTime),
												session.getAttribute("clientIP").toString(), ipaddress.getIPAddressAsLong(), 0,
												request.getHeader("User-Agent"), null);
										
				
				saveLoginSession(loginSessionInterface);
				
				// saving userId
				saveSessionData(setSessionData(session.getAttribute("userId").toString(), session.getAttribute("userId").toString(), DataType.STRING.getValue(), null,
						userBean.getProperties().get("userId").toString(), null, null, null, session.getAttribute("usidHash").toString(), "userId"));
				

				// saving usidHash
				saveSessionData(setSessionData(session.getAttribute("userId").toString(), session.getAttribute("userId").toString(), DataType.STRING.getValue(), null,
						session.getAttribute("usidHash").toString(), null, null, null, session.getAttribute("usidHash").toString(), "usidHash"));
				
				// saving usidKeyHash
				saveSessionData(setSessionData(session.getAttribute("userId").toString(), session.getAttribute("userId").toString(), DataType.JSON.getValue(), null, null,
						session.getAttribute(session.getAttribute("usidHash").toString()).toString(), null, null, session.getAttribute("usidHash").toString(), session
						.getAttribute("usidHash").toString()));
				
				// saving qkeyHash
				saveSessionData(setSessionData(session.getAttribute("userId").toString(), session.getAttribute("userId").toString(), DataType.STRING.getValue(), null,
						session.getAttribute("qKeHash").toString(), null, null, null, session.getAttribute("usidHash").toString(), "qKeHash"));
				
				// saving loginId
				saveSessionData(setSessionData(session.getAttribute("userId").toString(), session.getAttribute("userId").toString(),DataType.STRING.getValue(), null,
						session.getAttribute("loginId").toString(), null, null, null, session.getAttribute("usidHash").toString(), "loginId"));
				// saving clientIpPort
				saveSessionData(setSessionData(session.getAttribute("userId").toString(), session.getAttribute("userId").toString(), DataType.STRING.getValue(), null,
						session.getAttribute("clientIP").toString(), null, null, null, session.getAttribute("usidHash").toString(), "clientIP"));
				
				// saving clientPort
				saveSessionData(setSessionData(session.getAttribute("userId").toString(), session.getAttribute("userId").toString(), DataType.NUMBER.getValue(),
						Integer.parseInt(session.getAttribute("clientPort").toString()), null, null, null, null, session.getAttribute("usidHash").toString(), "clientPort"));
			
				// saving contactId
				saveSessionData(setSessionData(session.getAttribute("userId").toString(), session.getAttribute("userId").toString(), DataType.STRING.getValue(), null,
						session.getAttribute("contactId").toString(), null, null, null, session.getAttribute("usidHash").toString(), "contactId"));
				
				// saving TIMEZONE FORMAT
				saveSessionData(setSessionData(session.getAttribute("userId").toString(), session.getAttribute("userId").toString(), DataType.STRING.getValue(), null,
						session.getAttribute("timeZone").toString(), null, null, null, session.getAttribute("usidHash").toString(), "timeZone"));
				
				// saving TIMEZONEID
				saveSessionData(setSessionData(session.getAttribute("userId").toString(), session.getAttribute("userId").toString(), DataType.STRING.getValue(), null,
						session.getAttribute("timeZoneId").toString(), null, null, null, session.getAttribute("usidHash").toString(), "timeZoneId"));
				
				if (runtimeLogInfoHelper.getCustomerId() != null && !runtimeLogInfoHelper.getCustomerId().equalsIgnoreCase("SYSTEM_CUSTOMER")) {
					// saving CUSROMERiD
					saveSessionData(setSessionData(session.getAttribute("userId").toString(), session.getAttribute("userId").toString(), DataType.STRING.getValue(), null,
							session.getAttribute("multitenantId").toString(), null, null, null, session.getAttribute("usidHash").toString(), "multitenantId"));
				}
			} catch (final PersistenceException e) {
				e.printStackTrace();
			}
		}

		session.setAttribute("userAccessCode", userBean.getProperties().get("userAccessCode").toString());
		new JSONObjectMapperHelper().toJsonString(userBean.getProperties().get("userAccessCode").toString());
		// Saving userAccessCode
		saveSessionData(setSessionData(session.getAttribute("userId").toString(), session.getAttribute("userId").toString(), DataType.NUMBER.getValue(), null, null,
				userBean.getProperties().get("userAccessCode").toString(), null, null, session.getAttribute("usidHash").toString(), "userAccessCode"));
	}
	
	/**
	 * setting cookies in response object
	 *
	 * @param userBean
	 * @param session
	 * @param request
	 * @param response
	 */
	public void setCookieInSession(final UserBean userBean, final HttpSession session, final HttpServletRequest request, final HttpServletResponse response) {
		
		final Cookie cookie = cookieValidation.getSessionCookies(session, request, userBean, session.getAttribute("qKeHash").toString(), false);
		response.addCookie(cookie);
		final Cookie timeZoneCookie = cookieValidation.setTimeZoneCookie(session, request, userBean, false);
		response.addCookie(timeZoneCookie);
	}
	
	
	private SessionDataInterface setSessionData(String customerID, String userId, int dataType, Integer numberValue, String stringValue, 
			String jsonValue, Boolean booleanValue, Timestamp dateTimeValue, String appSessionId, String sessionKey) {
		SessionDataInterface sessionDataInterface = new SessionData();
		sessionDataInterface.setCustomerId(customerID);
		sessionDataInterface.setUserId(userId);
		sessionDataInterface.setDataType(dataType);
		sessionDataInterface.setNumberValue(numberValue);
		sessionDataInterface.setStringValue(stringValue);
		sessionDataInterface.setJsonValue(jsonValue);
		sessionDataInterface.setBooleanValue(booleanValue);
		sessionDataInterface.setDateTimeValue(dateTimeValue);
		sessionDataInterface.setAppSessionId(appSessionId);
		sessionDataInterface.setSessionKey(sessionKey);
		return sessionDataInterface;
	}
	
	/**
	 * @param session
	 * @throws Exception
	 */
	public void addUserAccessDatainSession(final HttpSession session) throws Exception {
		try {
			final String finalString = userAccessRightService.getUserAccessData();
			loginSessionDataRepository.saveSessionData(setSessionData(session.getAttribute("userId").toString(), session.getAttribute("userId").toString(), DataType.STRING.getValue(), null,
					finalString, null, null, null, session.getAttribute("usidHash").toString(), "userAccessQuery"));
		} catch (final Exception ex) {
			System.err.println("Error in addUserAccessDatainSession" + ex.getMessage());
		}
	}

}
