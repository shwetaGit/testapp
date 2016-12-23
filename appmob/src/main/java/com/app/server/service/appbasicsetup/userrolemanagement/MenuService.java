package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.businessservice.appbasicsetup.userrolemanagement.MenuBusinessService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.athena.server.pluggable.utils.bean.FindByBean;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import com.athena.server.pluggable.utils.bean.JsonIdBean;

@Controller
@RequestMapping(value = "/MenuService", method = RequestMethod.POST)
public class MenuService {

	@Autowired
	MenuBusinessService menuBusinessService;

	/**
	 * Service use to get the menus for main screen and all home screens for the
	 * respected role
	 */
	@RequestMapping(value = "fetchmenus")
	public HttpEntity<ResponseBean> fetchMenus(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseBean bean = new ResponseBean();

		Object loggedInObject = session.getAttribute(session.getAttribute("usidHash").toString());
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		if (loggedInObject != null) {
			int deviceType;
			String userAgent = request.getHeader("user-agent");
			if (userAgent.matches(".*Mobile.*")) {
				/* Requesting by mobile device */
				deviceType = 2;
			} else {
				/* Requesting by other than mobile device */
				deviceType = 1;
			}

			bean.add("data", menuBusinessService.getUserMenu(session.getAttribute("userId") + "", deviceType));
		} else {

			bean.add("success", false);
			bean.add("message", "Please relogin");
		}

		return new org.springframework.http.ResponseEntity<ResponseBean>(bean, httpStatus);
	}

	/**
	 * Service use to store the menus for main screen and all home screens for the
	 * respected role
	 */
	@RequestMapping(value = "storeMenu")
	public HttpEntity<ResponseBean> storeMenu(@RequestBody JsonIdBean json, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseBean reponse = new ResponseBean();
		String loggedInObject = session.getAttribute("userId").toString();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		if (loggedInObject != null) {
			try {
				menuBusinessService.storeMenu(loggedInObject, json.getId(), json.toString());
				reponse.add("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				reponse.add("success", false);
			}
		} else {
			reponse.add("success", false);
		}

		return new org.springframework.http.ResponseEntity<ResponseBean>(reponse, httpStatus);
	}

	/**
	 * Service use to Delete the menus for main screen and all home screens for the
	 * respected role
	 */
	@RequestMapping(value = "deleteMenu")
	public HttpEntity<ResponseBean> deleteMenu(@RequestBody JsonIdBean json, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseBean reponse = new ResponseBean();
		String loggedInObject = session.getAttribute("userId").toString();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		if (loggedInObject != null) {
			try {
				menuBusinessService.deleteMenu(loggedInObject, json.getId());
				reponse.add("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				reponse.add("success", false);
			}
		} else {
			reponse.add("success", false);
		}

		return new org.springframework.http.ResponseEntity<ResponseBean>(reponse, httpStatus);
	}

	/**
	 * Service use to fetchStore the menus  for the
	 * respected role
	 */
	@RequestMapping(value = "fetchStoreMenu")
	public HttpEntity<ResponseBean> fetchStoreMenu(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseBean reponse = new ResponseBean();
		String loggedInObject = session.getAttribute("userId").toString();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		if (loggedInObject != null) {
			reponse.add("success", true);
			reponse.add("data", menuBusinessService.fetchStoreMenus(loggedInObject));
		} else {
			reponse.add("success", false);
		}

		return new org.springframework.http.ResponseEntity<ResponseBean>(reponse, httpStatus);
	}

	/**
	 * Service use to get the menus for main screen and all home screens for the
	 * respected role
	 */
	@RequestMapping(value = "findMainScreenMenus")
	public HttpEntity<ResponseBean> findMainScreenMenus(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseBean bean = new ResponseBean();

		Object loggedInObject = session.getAttribute(session.getAttribute("usidHash").toString());
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		if (loggedInObject != null) {
			int deviceType;
			String userAgent = request.getHeader("user-agent");
			if (userAgent.matches(".*Mobile.*")) {
				/* Requesting by mobile device */
				deviceType = 2;
			} else {
				/* Requesting by other than mobile device */
				deviceType = 1;
			}
			bean.add("data", menuBusinessService.findMainScreenMenus(session.getAttribute("userId")+"", deviceType));
			System.out.println(bean); 
		} else {
			bean.add("success", false);
			bean.add("message", "Please relogin");
		}
		return new org.springframework.http.ResponseEntity<ResponseBean>(bean, httpStatus);
	}

	/**
	 * Service use to get the all menus for role feature mapping according to
	 * device type
	 */
	@RequestMapping(value = "findMenusByDeviceType")
	public HttpEntity<ResponseBean> findMenusByDeviceType(@RequestBody FindByBean findByBean, HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResponseBean bean = new ResponseBean();

		Object loggedInObject = session.getAttribute(session.getAttribute("usidHash").toString());
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		if (loggedInObject != null) {
			bean.add("data", menuBusinessService.getUserMenu(session.getAttribute("userId") + "", (Integer) findByBean.getFindKey()));
		} else {
			bean.add("success", false);
			bean.add("message", "Please relogin");
		}
		return new org.springframework.http.ResponseEntity<ResponseBean>(bean, httpStatus);
	}
}
