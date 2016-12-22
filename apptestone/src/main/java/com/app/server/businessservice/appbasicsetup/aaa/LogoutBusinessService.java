package com.app.server.businessservice.appbasicsetup.aaa;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;

import com.athena.server.pluggable.utils.bean.ResponseBean;

public interface LogoutBusinessService {

	/**
	 * @param session
	 * @return logout LogoutBusinessService HttpEntity<ResponseBean>
	 */
	public HttpEntity<ResponseBean> logout(HttpSession session);
}
