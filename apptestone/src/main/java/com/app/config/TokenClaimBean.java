package com.app.config;
import com.app.config.appSetup.model.AppConfiguration;

import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TokenClaimBean extends AbstractTokenClaimBean {

	/* (non-Javadoc)
	 * @see com.app.config.AbstractTokenClaimBean#prepareSecurityPolicy()
	 * Use this method to set algorithm and encryption/decryption key
	 */
	
	@Autowired
	private AppConfiguration appConfiguration;
	
	@Override
	public void prepareSecurityPolicy() {
		try {
			super.algorithm = appConfiguration.getJwtConfig().getJwtAlgorithm();
			super.key = appConfiguration.getJwtConfig().getTokenKey();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.spartan.server.interfaces.TokenClaimBeanInterface#prepareClaimAttribute()
	 * Use this method to set token claim attributes
	 */
	@Override
	public void prepareClaimAttribute() {
		final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		final HttpSession session = request.getSession();
		if (session != null) {
			final Enumeration<String> listOfAttribute = session.getAttributeNames();
			while (listOfAttribute.hasMoreElements()) {
				final String name = listOfAttribute.nextElement();
				if (!name.equalsIgnoreCase(session.getAttribute("usidHash").toString())) {
					super.claimAttributes.put(name, session.getAttribute(name));
				}
			}
		}
	}

	@Override
	public void setAudience() {
//		super.setAudience(audience);
	}

	@Override
	public void setExpirationDate() {
		final Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 1);
		super.setExpirationDate(c.getTime());
	}

	@Override
	public void setId() {
		super.setId("1");
	}

	@Override
	public void setIssuer() {
//		super.setIssuer(issuer);
	}

	@Override
	public void setNotBefore() {
//		super.setNotBefore(notBefore);
	}

	@Override
	public void setSubject() {
//		super.setSubject(subject);
	}
}
