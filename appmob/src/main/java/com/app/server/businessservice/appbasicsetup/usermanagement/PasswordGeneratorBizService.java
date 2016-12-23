package com.app.server.businessservice.appbasicsetup.usermanagement;
import org.springframework.http.HttpEntity;

import com.athena.server.pluggable.utils.bean.ResponseBean;

public interface PasswordGeneratorBizService {

	HttpEntity<ResponseBean> generatePassword(final String findKey) throws Exception;

	HttpEntity<ResponseBean> updateUser(final String userJson) throws Exception;

	HttpEntity<ResponseBean> changePassword(final String data, String userId) throws Exception;

	HttpEntity<ResponseBean> forgetPassword(final String data) throws Exception;

	HttpEntity<ResponseBean> resetPassword(final String data) throws Exception;

	HttpEntity<ResponseBean> findSecurityQuestions(final String findKey) throws Exception;

	HttpEntity<ResponseBean> findLoggedInUser(final String loggedInUserId);

	HttpEntity<ResponseBean> checkValidityOfLoginId(final String findKey) throws Exception;

	HttpEntity<ResponseBean> getNewPassword() throws Exception;

	HttpEntity<ResponseBean> generateAndSavePassword(final String findKey) throws Exception;

	HttpEntity<ResponseBean> resetAndUpdatePassword(final String findKey) throws Exception;

}
