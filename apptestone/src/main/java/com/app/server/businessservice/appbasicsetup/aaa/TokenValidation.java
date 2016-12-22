package com.app.server.businessservice.appbasicsetup.aaa;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import com.spartan.server.interfaces.TokenValidatorInterface;

@Service
public class TokenValidation implements TokenValidatorInterface {

	@Override
	public boolean validateToken(final String token, final String key) {
		if (!token.equals("null") && token != null) {
			try {
				final Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(token).getBody();
				if (claims.getExpiration().getTime() < new Date().getTime()) {
					System.out.println("TOKEN IS EXPIRED");
					return false;
				}
				return true;
			} catch (final Exception e) {
				System.out.println("WRONG TOKEN INPUT");
			}
		}
		return false;
	}

	public Payload getPayloadData(final String token, final String key) {
		final Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(token).getBody();
		final String usidHash = claims.get("usidHash").toString();
		final String loginId = claims.get("loginId").toString();
		final Integer userAccessCode = Integer.parseInt(claims.get("userAccessCode").toString());
		final String contactId = claims.get("contactId").toString();
		final String timeZoneId = claims.get("timeZoneId").toString();
		final String timeZone = claims.get("timeZone").toString();
		final String CookieTokenName = claims.get("CookieTokenName").toString();
		final String userId = claims.get("userId").toString();
		final String qKeHash = claims.get("qKeHash").toString();
		final String clientIP = claims.get("clientIP").toString();
		final Integer clientPort = Integer.parseInt(claims.get("clientPort").toString());
		final Integer sessionTimeout = Integer.parseInt(claims.get("sessionTimeout").toString());
		return new Payload(usidHash, loginId, userAccessCode, contactId, timeZoneId, timeZone, CookieTokenName, userId, qKeHash, clientIP, clientPort, sessionTimeout);
	}

}
