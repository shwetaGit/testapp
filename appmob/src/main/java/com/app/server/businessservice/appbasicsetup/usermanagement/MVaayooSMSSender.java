package com.app.server.businessservice.appbasicsetup.usermanagement;
import com.app.shared.appbasicsetup.usermanagement.SMSConfig;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.util.Asserts;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;

import com.athena.server.pluggable.utils.helper.apicaller.APICaller;
import com.athena.server.pluggable.utils.helper.apicaller.APIResponse;

public class MVaayooSMSSender implements SMSSenderInterface {
	String apiConfiguration;
	String baseUrl;
	String userName;
	String password;
	String url;

	/**
	 * Method for creating connectors
	 * 
	 * @param smsConfig
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> prepareConnector(SMSConfig smsConfig) throws Exception {
		final Map<String, Object> properties = new HashMap<String, Object>();
		apiConfiguration = smsConfig.getConfigName();
		baseUrl = smsConfig.getBaseUrl();
		final JSONObject jsonData = new JSONObject(smsConfig.getJsonData());
		userName = jsonData.getString("userName");
		password = jsonData.getString("password");
		if(jsonData.has("requestParams")) {
			JSONArray jsonArray = jsonData.getJSONArray("requestParams");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				properties.put(jsonObject.getString("requestKey"), jsonObject.getString("requestValue"));
			}
		}
		return properties;
	}

	/**
	 * Method for creating payload
	 * 
	 * @param properties
	 */
	@Override
	public void preparePayload(Map<String, Object> properties) {
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(baseUrl).append("user=" + userName + ":" + password);
		if (!properties.isEmpty()) {
			for (final Entry<String, Object> entry : properties.entrySet()) {
				sbBuffer.append("&" + entry.getKey() + "=" + entry.getValue());
			}
		}
		url = sbBuffer.toString();
	}

	/**
	 * Method for sending SMS to one user
	 * 
	 * @param mobileNo
	 * @param message
	 * @return
	 * @throws IOException
	 */
	@Override
	public Map<String, Object> sendSms(String mobileNo, String message) throws IOException {
		APIResponse response = null;
		Map<String, Object> responseObject = new HashMap<String, Object>();
		Asserts.check(mobileNo != null && message != null, "Mobile number and message should not be null");
		url = url.replace("<receipientno>", mobileNo);
		url = url.replace("<msgtxt>", URLEncoder.encode(message, "UTF-8"));
		response = callURL();
		responseObject.put("Status", response.getMessage());

		return responseObject;
	}

	private APIResponse callURL() throws IOException {
		final HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		final APICaller caller = new APICaller(url, "GET", headers);
		return caller.getResponse();
	}

	/**
	 * Method for sending SMS to many user
	 * 
	 * @param mobileNo
	 * @param message
	 * @return
	 * @throws IOException
	 */
	@Override
	public Map<String, Object> sendSms(final List<String> mobileNo, final String message) throws IOException {
		APIResponse response = null;
		Asserts.check(!mobileNo.isEmpty() && message != null, "Mobile number and message should not be null");

		Map<String, Object> responseObject = new HashMap<String, Object>();
		StringBuffer sbBuffer = new StringBuffer();
		for (int i = 0; i < mobileNo.size(); i++) {
			if (i == 0) {
				sbBuffer.append(mobileNo.get(i));
			} else {
				sbBuffer.append("," + mobileNo.get(i));
			}
		}
		url = url.replace("<receipientno>", sbBuffer.toString());
		url = url.replace("<msgtxt>", URLEncoder.encode(message, "UTF-8"));
		response = callURL();
		responseObject.put("Status", response.getMessage());
		return responseObject;
	}

	/**
	 * Method for sending SMS to many user with different messages
	 * 
	 * @param mobileNo
	 * @param message
	 * @return
	 * @throws IOException
	 */
	@Override
	public List<Map<String, Object>> sendSms(final List<String> mobileNo, final List<String> message) throws IOException {
		Asserts.check(!mobileNo.isEmpty() && !message.isEmpty(), "Mobile number and message should not be null");
		Asserts.check(mobileNo.size() == message.size(), "Mobile numbers and messages should be of equal numbers");
		List<Map<String, Object>> responseObject = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < mobileNo.size(); i++) {
			for (int j = i; j < message.size(); j++) {
				final Map<String, Object> responseObject1 = sendSms(mobileNo.get(i), message.get(j));
				responseObject.add((HashMap<String, Object>) responseObject1);
			}

		}

		return responseObject;
	}

}
