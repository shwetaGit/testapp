package com.app.server.service.appbasicsetup.aaa;
import com.app.server.businessservice.appbasicsetup.aaa.LogoutBusinessService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.athena.server.pluggable.utils.bean.ResponseBean;

@RestController
@RequestMapping(value = "Logout", method = RequestMethod.POST, consumes = { "application/json", "application/xml" })
public class LogoutService {

	@Autowired
	private LogoutBusinessService logoutBusinessService;

	@RequestMapping(method = RequestMethod.POST)
	public HttpEntity<ResponseBean> logoutExecute(final HttpSession session) {
		return logoutBusinessService.logout(session);
	}
}
