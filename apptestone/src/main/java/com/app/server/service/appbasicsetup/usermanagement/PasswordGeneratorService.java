package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.businessservice.appbasicsetup.usermanagement.PasswordGeneratorBizService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.athena.server.pluggable.utils.bean.FindByBean;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;

@RestController
@Scope("prototype")
@RequestMapping(value = "/PasswordGenerator", method = RequestMethod.POST)
public class PasswordGeneratorService {

	@Autowired
	private PasswordGeneratorBizService passwordGeneratorService;

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@RequestMapping(value = "generatePassword", method = RequestMethod.POST)
	public HttpEntity<ResponseBean> generatePassword(@RequestBody FindByBean findByBean, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return passwordGeneratorService.generatePassword((String) findByBean.getFindKey());
	}

	@RequestMapping(value = "updateUser", method = RequestMethod.PUT)
	public HttpEntity<ResponseBean> updateUser(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return passwordGeneratorService.updateUser(data);
	}

	@RequestMapping(value = "changePassword", method = RequestMethod.PUT)
	public HttpEntity<ResponseBean> changePassword(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return passwordGeneratorService.changePassword(data, loggedInUserId(request));
	}

	@RequestMapping(value = "forgetPassword", method = RequestMethod.POST)
	public HttpEntity<ResponseBean> forgetPassword(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return passwordGeneratorService.forgetPassword(data);
	}

	@RequestMapping(value = "resetPassword", method = RequestMethod.PUT)
	public HttpEntity<ResponseBean> resetPassword(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return passwordGeneratorService.resetPassword(data);
	}

	@RequestMapping(value = "findSecurityQuestions", method = RequestMethod.POST)
	public HttpEntity<ResponseBean> findSecurityQuestions(@RequestBody FindByBean findByBean, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return passwordGeneratorService.findSecurityQuestions((String) findByBean.getFindKey());
	}

	@RequestMapping(value = "findLoggedInUser", method = RequestMethod.GET)
	public HttpEntity<ResponseBean> findLoggedInUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return passwordGeneratorService.findLoggedInUser(loggedInUserId(request));
	}

	@RequestMapping(value = "checkValidityOfLoginId", method = RequestMethod.POST)
	public HttpEntity<ResponseBean> checkValidityOfLoginId(@RequestBody FindByBean findByBean, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return passwordGeneratorService.checkValidityOfLoginId((String) findByBean.getFindKey());
	}

	private String loggedInUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session.getAttribute("userId").toString();

	}

	@RequestMapping(value = "getNewPassword", method = RequestMethod.POST)
	public HttpEntity<ResponseBean> getNewPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return passwordGeneratorService.getNewPassword();
	}

}
