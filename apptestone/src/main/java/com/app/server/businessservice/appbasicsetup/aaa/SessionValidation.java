package com.app.server.businessservice.appbasicsetup.aaa;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import atg.taglib.json.util.JSONObject;

import com.spartan.pluggable.exception.auth.AccessDeniedException;
import com.spartan.pluggable.exception.auth.SessionTimeOutException;
import com.spartan.pluggable.exception.core.BaseSecurityException;
import com.spartan.server.interfaces.LoginSessionInterface;
import com.spartan.server.interfaces.LoginSessionRepositoryInterface;
import com.spartan.server.interfaces.UserAuthentication;
import com.spartan.server.session.bizService.SessionDataMgtService;

/**
 * Validate session against each request. Its checked user exists in session, as
 * well as valid cookies are sent with request object else @Throws
 * BaseSecurityException * session attributes : usidHash : SHA_256 of (Concat of
 * session_id | login_id | remote_host | report_port | date_time |
 * encrypted_password) qKeHash : SHA_256 of (Concat of session_id | login_id |
 * remote_host | report_port | date_time | encrypted_password) SHA_256 of
 * (Concat of session_id | login_id | remote_host | report_port | date_time |
 * encrypted_password) : User obj ref clientIp : client ip clientHost : client
 * host loginId : loginId
 * **/
@Component
public class SessionValidation {

	@Autowired
	private LoginSessionRepositoryInterface loginSessionRepo;

	@Autowired
	private CookieValidation cookieValidation;

	@Autowired
	private SessionDataMgtService sessionDataService;

	public SessionValidation() {
		super();
	}

	@Autowired
	private UrlPatternDefinitions urlPatterns;

	/**
	 * Validating session for current request
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @throws BaseSecurityException
	 */
	public void validateSession(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws BaseSecurityException {

		{

			Object usidHash = session.getAttribute("usidHash");

			if (usidHash == null) {

				validateSessionForDB(session, request, response);
			}
			Object user = getUser(session);
			if (user == null) {
				System.err.println("Invalid Client request. (Need Authentication!)");
				throw new AccessDeniedException("Invalid Client request. (Need Authentication!)", "ABSAA154900400", new AccessDeniedException());
			}

			/* validates Ip address attack */
			validateIPAddress(session, request);

		}
	}

	private void validateSessionForDB(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws SessionTimeOutException {

		String cookieValue = cookieValidation.getCookieValue(request, "XA_ID");
		if (cookieValue.length() > 0) {
			try {
				LoginSessionInterface loginSessionIntf = loginSessionRepo.findById(cookieValue);
				if (loginSessionIntf != null) {

					Timestamp currentTime = new Timestamp(System.currentTimeMillis());

					String usidHash = (String) sessionDataService.getSessionData("usidHash");
					String userJson = (String) sessionDataService.getSessionData(usidHash).toString();
					JSONObject json = new JSONObject(userJson);
					JSONObject user = json.getJSONObject("user");
					int sessionTimeOut = user.getInt("sessionTimeout");

					if (currentTime.getTime() - loginSessionIntf.getLastAccessTime().getTime() < (sessionTimeOut * 1000)) {

						sessionDataService.getAllSessionAttributes(loginSessionIntf.getAppSessionId());

						session.setAttribute("CookieTokenName", "XA_ID");

					} else {
						throw new SessionTimeOutException("Session expired", "ABSAA254905401", new SessionTimeOutException());
					}
				}
			} catch (Exception e) {
				throw new SessionTimeOutException("Session expired", "ABSAA254905401", new SessionTimeOutException());
			}
		}
	}

	/**
	 * Validating requested url
	 * 
	 * @param request
	 * @return
	 */
	public boolean checkIgnoreURL(HttpServletRequest request) {
		AntPathRequestMatcher requestMatcher;
		boolean ignoreThisURL = false;
		String[] ignoreURLS = urlPatterns.getRequestFilterIgnoreUrlPatterns();
		if (ignoreURLS != null && ignoreURLS.length > 0) {

			for (byte i = 0; i < ignoreURLS.length; i++) {
				requestMatcher = new AntPathRequestMatcher(ignoreURLS[i]);
				boolean matched = requestMatcher.matches(request);
				if (matched) {
					ignoreThisURL = true;
					break;
				}
			}
		}
		return ignoreThisURL;
	}

	private boolean validateIPAddress(final HttpSession mapSession, final HttpServletRequest request) throws AccessDeniedException {
		if (request.getRemoteHost().equalsIgnoreCase(getAuthClientIP(mapSession))) {
			return true;
		}
		System.err.println("Invalid IP Address. Possible Session Attack from IP Address = " + request.getRemoteHost());
		throw new AccessDeniedException("Invalid IP Address. Possible Session Attack from IP Address = " + request.getRemoteHost(), "ABSAA154900400", new AccessDeniedException());
	}

	private final String getAuthClientIP(final HttpSession mapSession) throws AccessDeniedException {
		return (String) mapSession.getAttribute("clientIP");
	}

	private final Object getUser(final HttpSession mapSession) throws SessionTimeOutException {
		String usidHash = getSessionID(mapSession);
		if (usidHash != null) {
			return mapSession.getAttribute(usidHash);
		} else {
			System.err.println("Your session is expired.");
			throw new SessionTimeOutException("Your session is expired.", "ABSAA254905401", new SessionTimeOutException());
		}
	}

	private final String getSessionID(final HttpSession mapSession) {
		return (String) mapSession.getAttribute("usidHash");
	}

	private void setSessionDetails(HttpServletRequest request, HttpSession session, UserAuthentication user) {

		int userSessionTimeout = user.getSessionTimeout();
		session.setMaxInactiveInterval(userSessionTimeout);
		session.setAttribute("clientIP", request.getRemoteHost());
		session.setAttribute("clientPort", request.getRemotePort());

	}

	/**
	 * @param request
	 * @param session
	 * @param user
	 */
	public void setUserSessionData(HttpServletRequest request, HttpSession session, UserAuthentication user) {
	}
}
