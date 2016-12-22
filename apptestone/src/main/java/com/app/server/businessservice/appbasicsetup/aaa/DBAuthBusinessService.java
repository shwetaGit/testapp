package com.app.server.businessservice.appbasicsetup.aaa;
import com.app.bean.UserInfoBeanImpl;

import java.sql.Timestamp;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import com.athena.server.pluggable.utils.HashAlgorithms;
import com.spartan.pluggable.auth.LoginCredentials;
import com.spartan.pluggable.auth.PluggableAuthConnector;
import com.spartan.pluggable.auth.TokenCredential;
import com.spartan.pluggable.auth.UserBean;
import com.spartan.pluggable.exception.auth.AccountLockedException;
import com.spartan.pluggable.exception.auth.InvalidLoginIdException;
import com.spartan.pluggable.exception.auth.PasswordExpiredException;
import com.spartan.pluggable.exception.core.AppBaseException;
import com.spartan.pluggable.exception.core.BaseSecurityException;
import com.spartan.pluggable.exception.layers.db.PersistenceException;
import com.spartan.server.authenticate.repository.AuthenticateRepository;
import com.spartan.server.interfaces.LoginSessionInterface;
import com.spartan.server.interfaces.LoginSessionRepositoryInterface;
import com.spartan.server.interfaces.UserAuthentication;
import com.spartan.server.interfaces.UserInterface;
import com.spartan.server.interfaces.UserRepositoryInterface;
import com.spartan.server.password.interfaces.PasswordAlgoInterface;
import com.spartan.server.password.interfaces.PasswordAlgoRepositoryIntefrace;
import com.spartan.server.password.interfaces.PasswordPolicyInterface;
import com.spartan.server.password.interfaces.PasswordPolicyRepositoryInterface;
import com.spartan.server.session.bizService.SessionDataMgtService;

@Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DBAuthBusinessService implements PluggableAuthConnector {

	@Autowired
	AuthenticateRepository authenticateRepository;

	@Autowired
	PasswordAlgoRepositoryIntefrace passwordAlgoRepository;

	@Autowired
	private PasswordPolicyRepositoryInterface passwordPolicyRepository;

	@Autowired
	AuthenticatePassword authenticatePassword;

	@Autowired
	private UserRepositoryInterface userRepo;

	@Autowired
	private LoginSessionRepositoryInterface loginInterfaceRepository;

	@Autowired
	AuthenticateRepository loginInfoRepository;

	@Autowired
	private SessionDataMgtService sessionDataAttributeService;

	private UserInfoBeanImpl userBean;

	/**
	 * Authenticate the user based on the Login Credentials and returns the User
	 * Bean
	 *
	 * @param loginBean
	 * @return
	 * @throws BaseSecurityException
	 */
	@Override
	public boolean authenticate(final LoginCredentials loginBean) throws BaseSecurityException {
		boolean isAuthenticate = false;
		UserAuthentication userAuthentication = null;

		try {
			userAuthentication = authenticateRepository.getUserByLoginId(loginBean.getLoginId());
			if (userAuthentication != null) {
				isAuthenticate = checkUserAthentication(userAuthentication, loginBean.getPassword());
				if (isAuthenticate) {
					userBean = (UserInfoBeanImpl) getUserBean(userAuthentication);
				}
			} else {
				throw new PersistenceException();
			}
		} catch (final Exception e) {
			throw new InvalidLoginIdException("Invalid Login Id or Password", "ABSAA254901401", null);
		}

		return isAuthenticate;
	}

	/**
	 * Re-Authenticate with Token Credential
	 *
	 * @param tokenBean
	 * @return
	 * @throws BaseSecurityException
	 */
	@Override
	public boolean reAuthenticate(final TokenCredential tokenBean) throws BaseSecurityException {
		boolean isReAuthenticate = false;
		UserAuthentication userAuthentication = null;
		try {
			final LoginSessionInterface loginSessionInterface = loginInterfaceRepository.findById(tokenBean.getAppToken());
			userAuthentication = loginInfoRepository.findByUserId(loginSessionInterface.getUserId());
			if (userAuthentication != null) {
				isReAuthenticate = checkUserAthentication(userAuthentication, tokenBean.getTokenCredentials());
				if (isReAuthenticate) {
					userBean = (UserInfoBeanImpl) getUserBean(userAuthentication);
				}
			}

		} catch (final Exception e) {
			throw new InvalidLoginIdException("Invalid Login Id or Password", "ABSAA254901401", null);
		}
		return isReAuthenticate;
	}

	/**
	 * Returns the Authenticated User else Throws Runtime Exception
	 *
	 * @return
	 */
	@Override
	public UserBean getAuthenticatedUser() {
		return userBean;
	}

	/**
	 * Logout the User
	 *
	 * @return
	 */
	@Override
	public boolean logout() {
		boolean isLogout = false;
		LoginSessionInterface loginSession = null;
		final HttpSession session = getSession();
		try {
			loginSession = loginInterfaceRepository.findById(session.getAttribute("usidHash").toString());

			if (loginSession != null) {
				loginSession.setLogoutTime(new Timestamp(System.currentTimeMillis()));
				loginSession.setSystemInformation(CommonEntityInterface.RECORD_TYPE.DELETE);
				sessionDataAttributeService.removeSessionAllAttributes();
				loginInterfaceRepository.updateSession(loginSession);
				isLogout = true;
			}
		} catch (final Exception e) {

		}

		return isLogout;
	}

	/**
	 * Terminate the User Session
	 *
	 * @return
	 */
	@Override
	public boolean terminateSession() {
		final HttpSession session = getSession();
		session.invalidate();
		return true;
	}

	/**
	 * Multi Factor Authentication Using OTP
	 *
	 * 1. Token ID (integer or alpha numeric). OTP is usually a 4 or 6 Digit
	 * Numeric Value 2. App Session ID
	 *
	 * @param otpBean
	 * @return
	 * @throws BaseSecurityException
	 */
	@Override
	public boolean validateOTP(final TokenCredential tokenBean) throws BaseSecurityException {
		return false;
	}

	/**
	 * @return current session of logged in user
	 */
	private HttpSession getSession() {
		final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		final HttpSession session = request.getSession();
		return session;
	}

	/**
	 * Checking the user authentication
	 *
	 * @param userAuthentication
	 * @param password
	 * @param authentication
	 * @return
	 * @throws AppBaseException
	 */
	private boolean checkUserAthentication(final UserAuthentication userAuthentication, final String password) throws AppBaseException {
		String userEncodedPwd = "";
		boolean isUserAthentcate = false;

		if (userAuthentication.isDisabled()) {
			throw new AccountLockedException("Account Locked", "ABSAA254902401", null);
		}

		if (userAuthentication.isPasswordExpired()) {
			throw new PasswordExpiredException("Password Expired", "ABSUM343953403", null);
		}
		try {
			final UserInterface userData = userRepo.getByUserId(userAuthentication.getUserId());
			final Integer userAccountStatus = userData.getIsLocked();
			if (userAccountStatus != null && userAccountStatus == 1) {
				throw new AccountLockedException();
			} else {
				Boolean isPasswordValid = true;

				final PasswordPolicyInterface passwordPolicy = passwordPolicyRepository.findAll().get(0);

				isPasswordValid = authenticatePassword.isPasswordValid(password, passwordPolicy);
				if (isPasswordValid) {
					try {

						final PasswordAlgoInterface passwordAlgo = passwordAlgoRepository.findAll().get(0);
						userEncodedPwd = HashAlgorithms.getInstance().computeHash(password, passwordAlgo.getAlgorithm());

					} catch (final Exception e) {
						throw new InvalidLoginIdException("Invalid Login Id or Password", "ABSAA254901401", null);
					}

					if (userEncodedPwd.equals(userAuthentication.getCredential())) {
						isUserAthentcate = true;
					} else {
						throw new InvalidLoginIdException("Invalid Login Id or Password", "ABSAA254901401", null);
					}
				}

			}
		} catch (final Exception e) {
			throw new InvalidLoginIdException("Invalid Login Id or Password", "ABSAA254901401", null);
		}
		return isUserAthentcate;

	}

	/**
	 * Return the UserBean object
	 *
	 * @param userAuthntication
	 * @return
	 */
	private UserBean getUserBean(final UserAuthentication userAuthntication) {
		final UserInfoBeanImpl userBean = new UserInfoBeanImpl();
		userBean.setLoginID(userAuthntication.getLoginId());
		// need to discuss
		// userBean.setAthenticated(true);
		userBean.setLocked(userAuthntication.isDisabled());
		final HashMap<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("loginId", userAuthntication.getLoginId());
		propertiesMap.put("userId", userAuthntication.getUserId());

		propertiesMap.put("timeZone", userAuthntication.getCoreContacts().getTimezone().getGmtLabel());
		propertiesMap.put("timeZoneId", userAuthntication.getCoreContacts().getTimezone().getTimeZoneLabel());

		propertiesMap.put("credentials", userAuthntication.getCredential());
		propertiesMap.put("sessionTimeout", userAuthntication.getSessionTimeout());

		propertiesMap.put("contactId", userAuthntication.getCoreContacts().getContactId());
		propertiesMap.put("userAccessCode", userAuthntication.getuserAccessCode());

		propertiesMap.put("firstName", (userAuthntication.getCoreContacts().getFirstName() == null ? "-" : userAuthntication.getCoreContacts().getFirstName()));
		propertiesMap.put("middleName", (userAuthntication.getCoreContacts().getMiddleName() == null ? "-" : userAuthntication.getCoreContacts().getMiddleName()));
		propertiesMap.put("lastName", (userAuthntication.getCoreContacts().getLastName() == null ? "-" : userAuthntication.getCoreContacts().getLastName()));
		propertiesMap.put("emailId", userAuthntication.getCoreContacts().getEmailId());
		propertiesMap.put("phoneNumber", userAuthntication.getCoreContacts().getPhoneNumber());

		userBean.setProperties(propertiesMap);

		return userBean;

	}

}
