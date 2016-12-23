package com.app.server.businessservice.appbasicsetup.aaa;
import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.athena.server.pluggable.utils.bean.ResponseBean;
import com.spartan.pluggable.auth.PluggableAuthConnector;


@Service
public class LogoutBusinessServiceImpl implements LogoutBusinessService {

	@Autowired
	private PluggableAuthConnector userAuthenticator;

	/* (non-Javadoc)
	 * @see com.app.server.businessservice.appbasicsetup.aaa.LogoutBusinessService#logout(com.spartan.pluggable.auth.LoginCredentials, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	@Override
	public HttpEntity<ResponseBean> logout(final HttpSession session) {
		final org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		try {
			final boolean lououtStatus = userAuthenticator.logout();
			if (lououtStatus) {
				userAuthenticator.terminateSession();
			}

		} catch (final Exception e1) {
			e1.printStackTrace();
		}

		final ResponseBean responseBean = new ResponseBean();
		responseBean.add("success", "true");
		responseBean.add("message", "User logged out Successfully");
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

}
