package com.app.server.businessservice.appbasicsetup.usermanagement;
import com.app.shared.appbasicsetup.usermanagement.SMSConfig;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SMSSenderInterface {

	/**
	 * Method for creating connectors
	 * 
	 * @param smsConfig
	 * @throws Exception
	 */
	Map<String, Object> prepareConnector(SMSConfig smsConfig) throws Exception;

	/**
	 * Method for creating payload
	 * 
	 * @param properties
	 */
	void preparePayload(Map<String, Object> properties);

	/**
	 * Method for sending SMS to one user
	 * 
	 * @param mobileNo
	 * @param message
	 * @return
	 * @throws IOException
	 */
	Map<String, Object> sendSms(String mobileNo, String message) throws IOException;

	/**
	 * Method for sending SMS to many user
	 * 
	 * @param mobileNo
	 * @param message
	 * @return
	 * @throws IOException
	 */
	Map<String, Object> sendSms(List<String> mobileNo, String message) throws IOException;

	/**
	 * Method for sending SMS to many user with different messages
	 * 
	 * @param mobileNo
	 * @param message
	 * @return
	 * @throws IOException
	 */
	List<Map<String, Object>> sendSms(List<String> mobileNo, List<String> message) throws IOException;

}
