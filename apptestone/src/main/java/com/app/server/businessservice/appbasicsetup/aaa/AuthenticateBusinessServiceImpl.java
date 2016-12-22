package com.app.server.businessservice.appbasicsetup.aaa;
import com.app.config.appSetup.model.AppConfiguration;

import com.app.bean.TokenCredentialBean;

import com.app.config.TokenClaimBean;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.pluggable.auth.LoginCredentials;
import com.spartan.pluggable.auth.PluggableAuthConnector;
import com.spartan.pluggable.auth.TokenCredential;
import com.spartan.pluggable.auth.UserBean;
import com.spartan.pluggable.exception.auth.AccessDeniedException;
import com.spartan.pluggable.exception.auth.AccountExpired;
import com.spartan.pluggable.exception.auth.InvalidLoginIdException;
import com.spartan.pluggable.exception.auth.PasswordExpiredException;
import com.spartan.pluggable.exception.layers.db.PersistenceException;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.spartan.server.authenticate.repository.AuthenticateRepository;
import com.spartan.server.interfaces.LoginSessionDataRepository;
import com.spartan.server.interfaces.LoginSessionInterface;
import com.spartan.server.interfaces.LoginSessionRepositoryInterface;
import com.spartan.server.interfaces.SessionDataInterface;
import com.spartan.server.interfaces.TokenInitializerInterface;
import com.spartan.server.interfaces.UserInterface;
import com.spartan.server.interfaces.UserRepositoryInterface;
import com.spartan.server.session.bizService.SessionDataMgtService;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

@Service
public class AuthenticateBusinessServiceImpl implements AuthenticateBusinessService {
	
	@Autowired
	TokenInitializerInterface tokenGenerator;
	
	@Autowired
    TokenClaimBean clainBeanInterface;
	

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@Autowired
	private PluggableAuthConnector userAuthenticator;

	@Autowired
	private SessionDataGeneration sessionDataGeneration;

	@Autowired
	private CookieValidation cookieValidation;

	@Autowired
	private LoginSessionRepositoryInterface loginInterfaceRepository;

	@Autowired
	AuthenticateRepository authenticateRepository;

	@Autowired
	private SessionDataMgtService sessionDataAttribute;

	@Autowired
	private UserRepositoryInterface userRepo;

	@Autowired
	private AppConfiguration appConfig;

	@Autowired
	private LoginSessionDataRepository loginSessionDataRepository;
	
	@Autowired
	private CookieGeneration cookieGeneration;


	private final LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);


	/**
	 * Authenticate the user and Return Response object
	 *
	 * @param loginBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@Override
	public HttpEntity<ResponseBean> authenticate(@RequestBody final LoginCredentials loginBean, final HttpServletRequest request, final HttpServletResponse response, final HttpSession session) {

		final ResponseBean responseBean = new ResponseBean();
		boolean success = true;
		String message = "";
		UserBean userBean = null;
		AppAlarm appAlarm = null;
		try {
			try {
				final String customerId = authenticateRepository.getUserDetailsSQL(loginBean.getLoginId());
				if (customerId != null) {
					session.setAttribute("multitenantId", customerId);
					runtimeLogInfoHelper.setCustomerId(customerId);
				}
			} catch (final Exception e) {
				System.err.println("Column for MultitenantFields not found");
			}

			boolean isUserAthenticated = false;
			if (appConfig.getAuthenticationConfig().getAuthPlugin().getAuthType() == 1) {

				if (!userLoggedIn(session)) {
					isUserAthenticated = userAuthenticator.authenticate(loginBean);
					if (isUserAthenticated) {
						userBean = userAuthenticator.getAuthenticatedUser();
					}
				} else {
					isUserAthenticated = userAuthenticator.authenticate(loginBean);
					if (isUserAthenticated) {
						userBean = userAuthenticator.getAuthenticatedUser();
					}
				}
				if (userBean != null) {
					final UserInterface userData = userRepo.getByUserId(userBean.getProperties().get("userId").toString());
					final Integer changePasswordInNextLogin = userData.getChangeAuthNextLogin();
					if (changePasswordInNextLogin != null && changePasswordInNextLogin == 1) {
						responseBean.add("changePassword", true);
						success = false;
						message = "Your account is applicable to change password in next login!<br>For login you must change password";
					}

					/**
					 * ADDING FLAG TO SET SECURITY QUESTION WHEN FLAT IS TRUE
					 * USER GETS THE POP-UP TO SET SECURITY QUESTION FROM USER
					 * PROFILE OPTION
					 */
					boolean setSecutityQuestions = false;
					if (userData.getTotalNumberOfPassRecovery() == 0
							&& !(userData.getUserId().equals("18D01ABF-F632-496A-B379-FC50EDEAB8C0") || userData.getUserId().equals("F332F408-BED0-4F0C-BEAA-E007E8FFB21E"))) {
						setSecutityQuestions = true;
					}
					responseBean.add("setSecutityQuestions", setSecutityQuestions);
				}
			} else if (appConfig.getAuthenticationConfig().getAuthPlugin().getAuthType() == 2) {
				isUserAthenticated = userAuthenticator.authenticate(loginBean);
				if (isUserAthenticated) {
					userBean = userAuthenticator.getAuthenticatedUser();
				}
			}

			if (userBean != null) {
				
				saveDataInSession(userBean, session, request, response); //Set Session 
				
				setCookieInSession(userBean, response);//Set Cookie

				sessionDataGeneration.addUserAccessDatainSession(session); // Add Assess Data Session 
				
				message = "Authentication success";

			} else {
				throw new InvalidLoginIdException("Invalid Login Id or Password", "ABSAA254901401", null);
			}

			/* ADDING AUTHENTICATED USER DETAILS IN RESPONSE */
			final JSONObject userInfo = new JSONObject();
			saveUserInfo(userBean, userInfo);

			loginInterfaceRepository.updateLastAccessTime(userBean.getProperties().get("userId").toString(), session.getAttribute("usidHash").toString(),
					new Timestamp(System.currentTimeMillis()));
			/****
			 * Generating Token using jwt
			 */
			clainBeanInterface.prepareSecurityPolicy();
			clainBeanInterface.prepareClaimAttribute();
			clainBeanInterface.setAudience();
			clainBeanInterface.setSubject();
			clainBeanInterface.setIssuedAt();
			clainBeanInterface.setExpirationDate();
			final String token = tokenGenerator.getToken(clainBeanInterface);
			

			appAlarm = Log.getAlarm("ABSAA124900200");
			responseBean.addAlarm(appAlarm);
			responseBean.add("success", success);
			responseBean.add("message", message);
			responseBean.add("token", token);
			responseBean.add("userinfo", userInfo.toString());
			Log.out.println("ABSAA124900200", runtimeLogInfoHelper.getRequestHeaderBean(), "authenticate", loginBean.getLoginId());

		} catch (final InvalidLoginIdException e) {
			e.printStackTrace();
			appAlarm = Log.getAlarm(e.getAppAlarmId());
			final ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", String.format(appAlarm.getMessage(), e.getMessage()));
			Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "authenticate", loginBean.getLoginId(), "Invald loginId and Password");

		} catch (final AccountExpired e1) {
			e1.printStackTrace();
			appAlarm = Log.getAlarm("ABSAA254901401");
			final ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", appAlarm.getMessage());
			Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "authenticate", loginBean.getLoginId(), "Your Account is Expired");

		} catch (final PasswordExpiredException e2) {
			e2.printStackTrace();
			appAlarm = Log.getAlarm("ABSUM343953403");
			final ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", appAlarm.getMessage());
			Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "authenticate", loginBean.getLoginId(), "User details not found");

		} catch (final PersistenceException e2) {
			e2.printStackTrace();
			appAlarm = Log.getAlarm("ABSAA254901401");
			final ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", appAlarm.getMessage());
			Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "authenticate", loginBean.getLoginId(), "User details not found");

		} catch (final com.spartan.pluggable.exception.auth.AccountLockedException e3) {
			e3.printStackTrace();
			appAlarm = Log.getAlarm(e3.getAppAlarmId());
			final ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", appAlarm.getMessage());
			Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "authenticate", loginBean.getLoginId(),
					String.format(appAlarm.getMessage(), e3.getMessage()));
		} catch (final Exception e4) {
			e4.printStackTrace();
			appAlarm = Log.getAlarm("ABSAA154900400");
			final ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", " Error in creating entity" + e4.getMessage());
			Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "authenticate", loginBean.getLoginId(), e4);

		}
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));

	}

	/**
	 * Re-authenticate the user and Return Response object
	 *
	 * @param tokenBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@Override
	public HttpEntity<ResponseBean> reauthenticate(@RequestBody final TokenCredential tokenBean, final HttpServletRequest request, final HttpServletResponse response, final HttpSession session) {
		final ResponseBean responseBean = new ResponseBean();
		TokenCredentialBean tockenCredential = null;
		boolean success = true;
		String message = "";
		UserBean userBean = null;
		AppAlarm appAlarm = null;

		try {
			final Cookie[] cookies = request.getCookies();
			String sessionId = null;
			/** Checks the null value for cookies **/
			if (null != cookies) {
				/** Iteration for cookies presence **/
				for (final Cookie cookie : cookies) {
					if (cookie.getName().contains("XA_ID")) {
						sessionId = cookie.getValue();
					}
				}
			}
			/** For Multitent reauthentication **/
			final List<SessionDataInterface> sessiondatalst = loginSessionDataRepository.findByAppSessionId(sessionId);
			String loginId = "";
			for (int i = 0; i < sessiondatalst.size(); i++) {
				final SessionDataInterface sessionData = sessiondatalst.get(i);
				if (sessionData.getSessionKey().equalsIgnoreCase("loginId")) {
					loginId = sessionData.getStringValue();
					break;
				}
			}
			try {
				final String customerId = authenticateRepository.getUserDetailsSQL(loginId);
				if (customerId != null) {
					session.setAttribute("customerId", customerId);
					runtimeLogInfoHelper.setCustomerId(customerId);
				}
			} catch (final Exception e) {
				System.err.println("Column for MultitenantFields not found");
			}

			boolean isUserAthenticated = false;

			if (sessionId != null && !sessionId.isEmpty()) {
				tockenCredential = new TokenCredentialBean(tokenBean.getTokenCredentials(), sessionId);
			}

			if (appConfig.getAuthenticationConfig().getAuthPlugin().getAuthType() == 1) {

				isUserAthenticated = userAuthenticator.reAuthenticate(tockenCredential);

				if (isUserAthenticated) {
					userBean = userAuthenticator.getAuthenticatedUser();
				}
				if (userBean != null) {
					final UserInterface userData = userRepo.getByUserId(userBean.getProperties().get("userId").toString());
					final Integer changePasswordInNextLogin = userData.getChangeAuthNextLogin();

					if (changePasswordInNextLogin != null) {
						if (changePasswordInNextLogin == 1) {
							responseBean.add("changePassword", true);
							success = false;
							message = "Your account is applicable to change password in next login!<br>For login you must change password";
						}
					}
				}

			} else if (appConfig.getAuthenticationConfig().getAuthPlugin().getAuthType() == 2) {
				isUserAthenticated = userAuthenticator.reAuthenticate(tockenCredential);
				if (isUserAthenticated) {
					userBean = userAuthenticator.getAuthenticatedUser();
				}
			}
			if (userBean != null) {
				saveDataInSession(userBean, session, request, response);// Set Session Data

				setCookieInSession(userBean, response);//Set Cookie

				sessionDataGeneration.addUserAccessDatainSession(session); // Add Access Domain Session
				
				message = "Reauthentication success";
			} else {
				throw new InvalidLoginIdException("Invalid Login Id or Password", "ABSAA254901401", null);

			}
			/** ADDING AUTHENTICATED USER DETAILS IN RESPONSE **/
			final JSONObject userInfo = new JSONObject();
			saveUserInfo(userBean, userInfo);

			appAlarm = Log.getAlarm("ABSAA124900200");
			responseBean.addAlarm(appAlarm);
			responseBean.add("success", success);
			responseBean.add("message", message);
			responseBean.add("userinfo", userInfo.toString());

			Log.out.println("ABSAA124900200", runtimeLogInfoHelper.getRequestHeaderBean(), "reauthenticate", session.getAttribute("loginId").toString());

		} catch (final InvalidLoginIdException e) {
			e.printStackTrace();
			appAlarm = Log.getAlarm(e.getAppAlarmId());
			final ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", String.format(appAlarm.getMessage(), e.getMessage()));
			Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "reauthenticate", String.format(appAlarm.getMessage(), e.getMessage()));

		} catch (final AccountExpired e1) {
			e1.printStackTrace();
			appAlarm = Log.getAlarm("ABSAA254901401");
			final ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", appAlarm.getMessage());
			Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "reauthenticate", String.format(appAlarm.getMessage(), e1.getMessage()));

		} catch (final PasswordExpiredException e2) {
			e2.printStackTrace();
			appAlarm = Log.getAlarm("ABSUM343953403");
			final ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", appAlarm.getMessage());
			Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "reauthenticate", String.format(appAlarm.getMessage(), e2.getMessage()));

		} catch (final PersistenceException e2) {
			e2.printStackTrace();
			appAlarm = Log.getAlarm("ABSAA254904403");
			final ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", appAlarm.getMessage());
			Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "reauthenticate", String.format(appAlarm.getMessage(), e2.getMessage()));

		} catch (final com.spartan.pluggable.exception.auth.AccountLockedException e3) {
			e3.printStackTrace();
			appAlarm = Log.getAlarm(e3.getAppAlarmId());
			final ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", appAlarm.getMessage());
			Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "reauthenticate", String.format(appAlarm.getMessage(), e3.getMessage()));

		} catch (final Exception e4) {
			e4.printStackTrace();
			appAlarm = Log.getAlarm("ABSAA154900400");
			final ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", " Error in creating entity" + e4.getMessage());
			Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "reauthenticate", String.format(appAlarm.getMessage(), e4.getMessage()));
		}
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));

	}

	/**
	 * Return true if User session already available else return false
	 *
	 * @param session
	 * @return
	 */
	private boolean userLoggedIn(final HttpSession session) {
		LoginSessionInterface loginSession = null;
		try {
                       if(session.getAttribute("usidHash")!=null){
			loginSession = loginInterfaceRepository.findById(session.getAttribute("usidHash").toString());
                        }
		} catch (final PersistenceException e1) {
			System.err.println("Requested Session Id not found");
		} catch (final Exception e1) {
			System.err.println("Requested Session Id not found");
		}
		if (loginSession != null) {
			if (loginSession.getActiveStatus() == 1) {
				return true;
			} else {
				return false;
			}
		}

		else {
			return false;
		}
	}

	/**
	 * Checking Logged in status of current user and Return Response object
	 *
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@Override
	public HttpEntity<ResponseBean> checkLoginStatus(final HttpServletRequest request, final HttpServletResponse response, final HttpSession session) {

		final ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;

		try {
			final String cookieValue = cookieValidation.getCookieValue(request, "XA_ID");
			if (cookieValue.length() > 0) {
				try {
					final LoginSessionInterface loginSessionIntf = loginInterfaceRepository.findById(cookieValue);
					if (loginSessionIntf != null) {

						final Timestamp currentTime = new Timestamp(System.currentTimeMillis());

						final String usidHash = (String) sessionDataAttribute.getSessionData("usidHash");
						final String userJson = sessionDataAttribute.getSessionData(usidHash).toString();
						final JSONObject json = new JSONObject(userJson);
						final JSONObject user = json.getJSONObject("userDetail");
						final int sessionTimeOut = user.getInt("sessionTimeout");

						if ((currentTime.getTime() - loginSessionIntf.getLastAccessTime().getTime()) < (sessionTimeOut * 1000)) {

							responseBean.add("success", true);
							responseBean.add("message", "Already logged in");
						} else {
							responseBean.add("success", false);
							responseBean.add("message", " Error " + new AccessDeniedException("Session expired", "APSAN154101401", new AccessDeniedException()).getMessage());
						}
					}
				} catch (final Exception e) {
					e.printStackTrace();
					responseBean.add("success", false);
					responseBean.add("message", " Error " + e.getMessage());
					httpStatus = org.springframework.http.HttpStatus.BAD_REQUEST;
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			responseBean.add("success", false);
			responseBean.add("message", " Error " + e.getMessage());
			httpStatus = org.springframework.http.HttpStatus.BAD_REQUEST;
		}

		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

private void saveDataInSession(UserBean userBean, HttpSession session,  HttpServletRequest request, HttpServletResponse response) throws Exception{ 
	
	sessionDataGeneration.setRequest(request);
	sessionDataGeneration.setSession(session); 
	sessionDataGeneration.setUserAttributes(userBean.getProperties()); 
	
	int userSessionTimeout = Integer.parseInt(userBean.getProperties().get("sessionTimeout").toString());
	session.setMaxInactiveInterval(userSessionTimeout);
	sessionDataGeneration.setSession();
	sessionDataGeneration.setSessionClientIp();
	sessionDataGeneration.setSessionClientPort();
	sessionDataGeneration.setSessionLoginId();
	sessionDataGeneration.setSessionLoginId();
	sessionDataGeneration.setSessionUserId();
	sessionDataGeneration.setSessionTimeZone();
	sessionDataGeneration.setSessionTimeZoneId();
	sessionDataGeneration.setSessionTimeout();
	sessionDataGeneration.setSessionContactId();
	sessionDataGeneration.setSessionUserAccessCode();
	sessionDataGeneration.setSessionQKeHash();
	sessionDataGeneration.saveUserDataToSession(session, userBean.getProperties());
	sessionDataGeneration.setSessionUserId();
	
	sessionDataGeneration.saveSessionData(session, request, userBean);
	
}

private void setCookieInSession(UserBean userBean, HttpServletResponse response){
	
	Cookie sessionCookie = cookieGeneration.getCookie("XA_ID",userBean,false); 
	response.addCookie(sessionCookie);
	Cookie timeZoneCookie = cookieGeneration.getCookie("XA_TID",userBean,false); 
	response.addCookie(timeZoneCookie);
}

	/**
	 * Saving data in user Json object
	 *
	 * @param userBean
	 * @param userInfo
	 * @throws JSONException
	 */
	private void saveUserInfo(final UserBean userBean, final JSONObject userInfo) throws JSONException {
		userInfo.put("firstName", userBean.getProperties().get("firstName").toString());
		userInfo.put("middleName", userBean.getProperties().get("middleName").toString());
		userInfo.put("lastName", userBean.getProperties().get("lastName").toString());
		userInfo.put("emailId", userBean.getProperties().get("emailId").toString());
		userInfo.put("phoneNumber", userBean.getProperties().get("phoneNumber").toString());

		userInfo.put("XA_TID", userBean.getProperties().get("timeZoneId").toString());
	}
}
