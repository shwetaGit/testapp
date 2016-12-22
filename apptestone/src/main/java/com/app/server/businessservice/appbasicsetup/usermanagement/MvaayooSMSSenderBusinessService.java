package com.app.server.businessservice.appbasicsetup.usermanagement;
import com.app.shared.appbasicsetup.usermanagement.SMSConfig;

import com.app.bean.SMSData;

import com.app.server.repository.appbasicsetup.usermanagement.SMSNotificationRepository;

import com.app.shared.appbasicsetup.usermanagement.SMSNotification;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

@Component(value = "mvaayooSMSSenderBean")
public class MvaayooSMSSenderBusinessService implements SMSNotificationBusinessService {

	@Autowired
	private SMSNotificationRepository<SMSNotification> smsNotificationRepo;

	/**
	 * Method for sending SMS to single user
	 * 
	 * @param smsConfig
	 * @param smsTemplate
	 * @param mobileNo
	 * @return true or false
	 * @throws Exception
	 */
	@Override
	public boolean sendSMS(final SMSConfig smsConfig, final SMSData smsData, String mobileNo) throws Exception {
		boolean isSend = false;
		int state = getState(smsConfig);
		final SMSSenderInterface mVaayooSMSSender = new MVaayooSMSSender();
		final Map<String, Object> properties = mVaayooSMSSender.prepareConnector(smsConfig);
		mVaayooSMSSender.preparePayload(properties);
		final SMSNotification smsnot = smsNotificationRepo.save(setSmsNotification(mobileNo, smsData.getSmsText()));
		final Map<String, Object> responseObject = mVaayooSMSSender.sendSms(mobileNo, smsData.getSmsText());
		updateSmsNotification(getResponseArray(responseObject, state), smsnot);
		isSend = true;
		return isSend;
	}

	/**
	 * Method for sending SMS to multiple user
	 * 
	 * @param smsConfig
	 * @param smsTemplate
	 * @param mobileNos
	 * @return true or false
	 * @throws Exception
	 */
	@Override
	public boolean sendSMS(final SMSConfig smsConfig, final SMSData smsData, final List<String> mobileNos) throws Exception {
		boolean isSend = false;

		final SMSSenderInterface mVaayooSMSSender = new MVaayooSMSSender();
		final Map<String, Object> properties = mVaayooSMSSender.prepareConnector(smsConfig);
		mVaayooSMSSender.preparePayload(properties);
		int state = getState(smsConfig);
		int cnt = 1;
		final List<SMSNotification> lstsmsnot = new ArrayList<SMSNotification>();
		final List<String> lstMobileNos = new ArrayList<String>();
		for (final Iterator iterator = mobileNos.iterator(); iterator.hasNext();) {
			final String mobileNo = (String) iterator.next();
			final SMSNotification smsnot = smsNotificationRepo.save(setSmsNotification(mobileNo, smsData.getSmsText()));
			lstsmsnot.add(smsnot);
			lstMobileNos.add(mobileNo);
			if (cnt == 25) {
				final Map<String, Object> responseObject = mVaayooSMSSender.sendSms(lstMobileNos, smsData.getSmsText());
				updateSmsNotification(responseObject, lstsmsnot, state);
				cnt = 1;
				lstsmsnot.removeAll(lstsmsnot);
				lstMobileNos.removeAll(lstMobileNos);
			}
			cnt++;
		}
		if (cnt < 25) {
			final Map<String, Object> responseObject = mVaayooSMSSender.sendSms(lstMobileNos, smsData.getSmsText());
			updateSmsNotification(responseObject, lstsmsnot, state);
		}
		isSend = true;

		return isSend;
	}

	/**
	 * Update SMSNotificatopn in database
	 * 
	 * @param responseArray
	 * @param smsNotification
	 * @throws Exception
	 */
	private void updateSmsNotification(final JSONArray responseArray, final SMSNotification smsNotification) throws Exception {
		if (smsNotification != null) {
			final JSONObject responseJson = new JSONObject();
			smsNotification.setIsSent(true);
			responseJson.put("responseStatus", responseArray);
			smsNotification.setResponseStatus(responseJson.toString());
			smsNotification.setSendDate(new Timestamp(System.currentTimeMillis()));
			smsNotificationRepo.update(smsNotification);
		}
	}

	/**
	 * creating SmsNotifiction object
	 * 
	 * @param mobileNo
	 * @param smsText
	 * @return SMSNotification
	 */
	private SMSNotification setSmsNotification(final String mobileNo, final String smsText) {
		final SMSNotification smsNotification = new SMSNotification();
		smsNotification.setMobileNo(mobileNo);
		smsNotification.setSmsText(smsText);
		smsNotification.setIsSent(false);
		return smsNotification;
	}

	/**
	 * @param responseObject
	 * @return JSONArray
	 * @throws JSONException
	 */
	private JSONArray getResponseArray(final Map<String, Object> responseObject, int state) throws JSONException {
		final JSONArray responseJsonArray = new JSONArray();
		if (!responseObject.isEmpty()) {
			for (final Entry<String, Object> entry : responseObject.entrySet()) {
				final String[] responseString = entry.getValue().toString().split(",");
				final JSONObject innerJson = new JSONObject();
				innerJson.put(responseString[0].split("=")[0], responseString[0].split("=")[1]);
				switch (state) {
				case 1:
					innerJson.put("TransactionId", responseString[1].trim());
					break;
				case 2:
					innerJson.put("JobId", responseString[1].trim());
					break;
				case 3:
					innerJson.put("JobId", responseString[1]);
					innerJson.put("TransactionId", responseString[2].trim());
					break;
				case 4:
					innerJson.put("TransactionId", responseString[1].trim());
					break;

				default:
					break;
				}
				responseJsonArray.add(innerJson);

			}
		}
		return responseJsonArray;
	}

	/**
	 * Update SMSNotificatopn in database
	 * 
	 * @param responseObject
	 * @param lstsmsnot
	 * @throws JSONException
	 * @throws Exception
	 */
	private void updateSmsNotification(final Map<String, Object> responseObject, final List<SMSNotification> lstsmsnot, int state) throws JSONException, Exception {

		for (final Entry<String, Object> entry : responseObject.entrySet()) {
			final String[] responseString = entry.getValue().toString().split(",");
			switch (state) {
			case 1:
				for (int i = 1; i < responseString.length; i++) {
					final Map<String, Object> responseObject1 = new HashMap<String, Object>();
					responseObject1.put("Status", responseString[0] + "," + responseString[i].trim());
					updateSmsNotification(getResponseArray(responseObject1, state), (SMSNotification) lstsmsnot.get(i - 1));
				}
				break;
			case 2:
				for (int i = 0; i < lstsmsnot.size(); i++) {
					final Map<String, Object> responseObject1 = new HashMap<String, Object>();
					responseObject1.put("Status", responseString[0] + "," + responseString[i].trim());
					updateSmsNotification(getResponseArray(responseObject1, state), (SMSNotification) lstsmsnot.get(i));
				}
				break;
			case 3:
				for (int i = 2; i < responseString.length; i++) {
					final Map<String, Object> responseObject1 = new HashMap<String, Object>();
					responseObject1.put("Status", responseString[0] + "," + responseString[1] + "," + responseString[i].trim());
					updateSmsNotification(getResponseArray(responseObject1, state), (SMSNotification) lstsmsnot.get(i - 2));
				}
				break;
			case 4:
				for (int i = 0; i < lstsmsnot.size(); i++) {
					final Map<String, Object> responseObject1 = new HashMap<String, Object>();
					responseObject1.put("Status", responseString[0] + ","+ responseString[i].trim());
					updateSmsNotification(getResponseArray(responseObject1, state), (SMSNotification) lstsmsnot.get(i));
				}
				break;

			default:
				for (int i = 0; i < lstsmsnot.size(); i++) {
					final Map<String, Object> responseObject1 = new HashMap<String, Object>();
					responseObject1.put("Status", responseString[0]);
					updateSmsNotification(getResponseArray(responseObject1, state), (SMSNotification) lstsmsnot.get(i));
				}
				break;
			}
			

		}

	}

	private int getState(SMSConfig smsConfig) throws JSONException, Exception {
		int state = -1;
		JSONObject jsonData = new JSONObject(smsConfig.getJsonData());
		if (jsonData.has("requestParams")) {
			JSONArray requestParams = new JSONArray(jsonData.getJSONArray("requestParams"));
			for (int i = 0; i < requestParams.length(); i++) {
				JSONObject params = requestParams.getJSONObject(i);
				if (params.getString("requestKey").equalsIgnoreCase("state")) {
					state = Integer.parseInt(params.getString("requestValue"));
				}

			}
		}
		return state;
	}

}
