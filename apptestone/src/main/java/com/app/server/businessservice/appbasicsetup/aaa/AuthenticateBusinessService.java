package com.app.server.businessservice.appbasicsetup.aaa;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.athena.server.pluggable.utils.bean.ResponseBean;
import com.spartan.pluggable.auth.LoginCredentials;
import com.spartan.pluggable.auth.TokenCredential;

public interface AuthenticateBusinessService {

	/**
	 * Authenticate the user and Return Response object with user information
	 * 
	 * @param loginBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	HttpEntity<ResponseBean> authenticate(@RequestBody LoginCredentials loginBean, HttpServletRequest request, HttpServletResponse response, HttpSession session);

	/**
	 * Re-authenticate the user and Return Response object with user information
	 * 
	 * @param tokenBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	HttpEntity<ResponseBean> reauthenticate(TokenCredential tokenBean, HttpServletRequest request, HttpServletResponse response, HttpSession session);

	/**
	 * Checking Logged in status of current user and Return Response object
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	HttpEntity<ResponseBean> checkLoginStatus(HttpServletRequest request, HttpServletResponse response, HttpSession session);
}
