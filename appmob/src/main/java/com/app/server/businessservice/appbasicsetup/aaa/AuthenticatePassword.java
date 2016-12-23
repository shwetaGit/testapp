package com.app.server.businessservice.appbasicsetup.aaa;
import com.app.util.PasswordGenerator;

import com.app.bean.PasswordLogic;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Component;

import com.spartan.server.password.interfaces.PasswordPolicyInterface;

@Component
public class AuthenticatePassword {

	public Boolean isPasswordValid(String password, PasswordPolicyInterface pwdPolicy) {
		String message = "";
		try {
			message = validatePassword(password, pwdPolicy);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (message.equals("success")) {
			return true;
		} else {
			return false;
		}
	}

	public String validatePassword(String password, PasswordPolicyInterface pwdPolicy) throws Exception {
		if (pwdPolicy != null) {
			PasswordGenerator passwordGenerator = new PasswordGenerator(pwdPolicy.getMinPwdLength(), pwdPolicy.getMaxPwdLength());
			if (pwdPolicy.getAllowedSpecialLetters() != null && pwdPolicy.getAllowedSpecialLetters().length() > 0) {
				char[] charArray = pwdPolicy.getAllowedSpecialLetters().toCharArray();
				Character[] speChars = ArrayUtils.toObject(charArray);
				passwordGenerator.setSPECIAL_LETTERS(speChars);
			}
			PasswordLogic[] pwdLogic = new PasswordLogic[] { new PasswordLogic('C', pwdPolicy.getMinCapitalLetters()), new PasswordLogic('S', pwdPolicy.getMinSmallLetters()),
					new PasswordLogic('N', pwdPolicy.getMinNumericValues()), new PasswordLogic('P', pwdPolicy.getMinSpecialLetters()) };
			if (!passwordGenerator.validatePwdData(pwdLogic)) {
				return "Incorrect Password Policy Configuration Data Found.";
			}
			String msg = passwordGenerator.validatePassword(pwdLogic, password);

			if (msg == null)// password is valid
			{
				return "success";

			} else
				return msg;

		} else
			return "password policy not defined";
	}
}
