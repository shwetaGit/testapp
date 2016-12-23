package com.app.server.businessservice.appbasicsetup.aaa;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.spartan.pluggable.auth.UserBean;
import com.spartan.pluggable.exception.auth.AccessDeniedException;

@Component
public class CookieValidation {
	final private String COOKIE_ID = "XA_ID";
	/**
	 * Return cookie for requested key
	 *
	 */
	public Cookie getSessionCookies(final HttpSession session, final HttpServletRequest request, final UserBean user, final String qkey, final boolean isSecure) {
		Cookie cookie = null;
		try {

			final int actualTimeout = Integer.parseInt(user.getProperties().get("sessionTimeout").toString());
			session.setAttribute("CookieTokenName", COOKIE_ID);
			// Token Name
			cookie = new Cookie(COOKIE_ID, qkey);
			if (isSecure) {
				cookie.setSecure(true);
				cookie.setHttpOnly(true);
			}
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(actualTimeout * 100);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return cookie;
	}

	/**
	 * Setting timeZoneId in Cookie object
	 *
	 */
	public Cookie setTimeZoneCookie(final HttpSession session, final HttpServletRequest request, final UserBean user, final boolean isSecure) {
		Cookie cookie = null;
		try {

			final int actualTimeout = Integer.parseInt(user.getProperties().get("sessionTimeout").toString());
			cookie = new Cookie("XA_TID", user.getProperties().get("timeZoneId").toString());
			if (isSecure) {
				cookie.setSecure(true);
				cookie.setHttpOnly(true);
			}
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(actualTimeout * 100);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return cookie;
	}

	/**
	 * Validates the session cookies
	 *
	 * */
	public void validateSessionCookie(final HttpSession mapSession, final HttpServletRequest request) throws AccessDeniedException {
		validateQKESessionID(mapSession, request);
	}

	/**
	 * Validate the XTOKEN value from the 512 Bit message digest
	 *
	 * @return
	 * @throws AccessDeniedException
	 */
	private boolean validateQKESessionID(final HttpSession mapSession, final HttpServletRequest request) throws AccessDeniedException {
		final String qKeSessionId = (String) mapSession.getAttribute("qKeHash");
		if (qKeSessionId == null) {
			System.err.println("Invalid Cookie SessionID. Possible Session Attack from IP Address = " + request.getRemoteHost());
			throw new AccessDeniedException("Invalid Cookie SessionID. Possible Session Attack from IP Address = " + request.getRemoteHost(), "ABSAA154900400",
					new AccessDeniedException());
		}

		// Compare the Cookie App Session ID with the qKe stored in the Users
		// Session
		if (!qKeSessionId.equalsIgnoreCase(getQKESessionIDFromCookies(mapSession, request))) {
			System.err.println("Session ID Expired. Possible Session Attack from IP Address = " + request.getRemoteHost());
			throw new AccessDeniedException("Session ID Expired. Possible Session Attack from IP Address = " + request.getRemoteHost(), "ABSAA154900400",
					new AccessDeniedException());
		}

		return true;
	}

	/**
	 * Get XTOKEN from Cookies
	 *
	 * @return String
	 * @throws AccessDeniedException
	 */
	private String getQKESessionIDFromCookies(final HttpSession mapSession, final HttpServletRequest request) throws AccessDeniedException {
		String cookieValue = null;
		final String cookieTokenName = getCookieTokenName(mapSession);
		for (final Cookie cookie : request.getCookies()) {
			if (cookie.getName().equalsIgnoreCase(cookieTokenName)) {
				cookieValue = cookie.getValue();
				break;
			}
		}
		if (cookieValue == null) {
			System.err.println("Invalid Request Cookie. Possible Session Attack from IP Address = " + request.getRemoteHost());
			throw new AccessDeniedException("Invalid Request Cookie. Possible Session Attack from IP Address = " + request.getRemoteHost(), "ABSAA154900400",
					new AccessDeniedException());
		}
		return cookieValue;
	}

	/**
	 * Get the Cookie Token Name which is generated based the following
	 *
	 * XA + User Application ID
	 *
	 * @return
	 * @throws AccessDeniedException
	 */
	private final String getCookieTokenName(final HttpSession mapSession) throws AccessDeniedException {
		return (String) mapSession.getAttribute("CookieTokenName");
	}

	/**
	 * Return cookie value from requested key
	 *
	 * @param request
	 * @param key
	 * @return
	 */
	public String getCookieValue(final HttpServletRequest request, final String key) {
		String cookieValue = "";
		if (request.getCookies() != null) {
			for (final Cookie cookie : request.getCookies()) {
				if (cookie.getName().equalsIgnoreCase(key)) {
					cookieValue = cookie.getValue();
					break;
				}
			}
		}
		return cookieValue;
	}

	/**
	 * Return cookie name from request object
	 *
	 * @param request
	 * @return
	 */
	public String getCookieName(final HttpServletRequest request) {
		String cookieName = "";
		for (final Cookie cookie : request.getCookies()) {
			if (cookie.getName().startsWith("XA_ID")) {
				cookieName = cookie.getName();
				break;
			}
		}

		if (cookieName.length() > 0) {
			cookieName = cookieName.substring(2);
		}
		return cookieName;
	}
}
