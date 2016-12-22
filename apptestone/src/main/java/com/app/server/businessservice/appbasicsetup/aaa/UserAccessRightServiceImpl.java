package com.app.server.businessservice.appbasicsetup.aaa;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

import com.spartan.server.interfaces.ArtQueryInterface;
import com.spartan.server.others.QueryRepository;
import com.spartan.server.session.bizService.SessionDataMgtService;

@Component
public class UserAccessRightServiceImpl implements UserAccessRightService {

	private final String DEFAULT_NOT_ACCESS_RIGHT = "isUserAccessible=0";

	@Autowired
	private SessionDataMgtService sessionDataAttribute;

	@Autowired
	QueryRepository queryRepository;

	/**
	 * Setting user Data Access in session
	 * 
	 * @throws Exception
	 */
	@Override
	public void addUserAccessDatainSession() throws Exception {

		try {
			String finalString = getUserAccessData();

			sessionDataAttribute.setSessionAttributeForString("userAccessQuery", finalString);
		} catch (Exception ex) {
			System.err.println("Error in addUserAccessDatainSession" + ex.getMessage());
		}
	}

	/**
	 * Return UserData Access Query
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getUserAccessData() throws Exception {

		String finalString = "";
		try {
			List<ArtQueryInterface> oList = queryRepository.getQueryForUserAccess();
			if (oList != null && oList.size() > 0) {
				finalString = buildCondition(oList);
			}

			if (finalString == null || finalString.length() == 0) {
				finalString = "AND " + DEFAULT_NOT_ACCESS_RIGHT;

			}

		} catch (Exception ex) {
			System.err.println("Error in addUserAccessDatainSession" + ex.getMessage());
		}
		return finalString;
	}

	private String buildCondition(List<ArtQueryInterface> oList) throws Exception {
		StringBuilder paramStr = new StringBuilder();
		for (ArtQueryInterface artQuery : oList) {
			JSONObject params = new JSONObject();
			setQueryParams(artQuery, params);
			JSONArray data = new JSONArray();
			data = getAllQueryData(params);
			StringBuilder localStr = buildActualCondition(data);
			if (localStr.length() > 0)
				paramStr.append(" AND " + localStr);
		}
		if (paramStr.length() > 0)
			paramStr.append(" OR " + DEFAULT_NOT_ACCESS_RIGHT);
		return paramStr.toString();

	}

	private StringBuilder buildActualCondition(JSONArray data) throws JSONException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length(); i++) {
			JSONObject currentJson = data.getJSONObject(i);
			StringBuilder localStr = builderConditionForIndividualRAW(currentJson);
			if (sb.length() > 0) {
				sb.append(" OR ");
			}
			sb.append(localStr);
		}
		return new StringBuilder(sb.toString().length() > 0 ? " ( " + sb.toString() + " ) " : "");

	}

	private StringBuilder builderConditionForIndividualRAW(JSONObject jsonObject) throws JSONException {
		StringBuilder sb = new StringBuilder();
		for (Iterator iterator = jsonObject.keys(); iterator.hasNext();) {

			String key = (String) iterator.next();
			if (sb.length() > 0) {
				sb.append(" AND ");
			}
			sb.append(key + ":" + jsonObject.getString(key));
		}
		return new StringBuilder(" ( " + sb.toString() + " ) ");
	}

	private void setQueryParams(ArtQueryInterface querydetails, JSONObject params) throws Exception {
		try {

			JSONObject queryInfo = new JSONObject();
			queryInfo.put("jpqlQuery", querydetails.getJpqlQuery());
			queryInfo.put("queryJSON", querydetails.getQueryJSON());
			queryInfo.put("queryType", querydetails.getQueryType());

			params.put("queryCriteria", createCriteriaJson(querydetails));
			params.put("queryInfo", queryInfo);
		} catch (JSONException ex) {
			throw ex;
		}

	}

	private JSONArray createCriteriaJson(ArtQueryInterface querydetails) throws Exception {
		String queryJson = querydetails.getQueryJSON();
		JSONArray criterialJson = new JSONArray();
		if (queryJson != null && queryJson.length() > 0) {
			JSONObject mainJson = new JSONObject(queryJson);
			if (mainJson.has("inputParams")) {
				criterialJson = mainJson.getJSONArray("inputParams");
				if (criterialJson != null && criterialJson.length() > 0) {
					for (int index = 0; index < criterialJson.length(); index++) {
						JSONObject currJson = criterialJson.getJSONObject(index);
						currJson.put("name", currJson.getString("attributeName"));
						currJson.put("datatype", currJson.getString("dataType"));
					}
				}
			}

		}
		return criterialJson;
	}

	public JSONArray getAllQueryData(JSONObject params) throws Exception {
		JSONArray data = new JSONArray();
		try {
			/***
			 * Replacing value of Session in Query Criterial
			 */
			changeSessionDataInQueryCriterial(params);
			/*
			 * excecute the query,return the Object of result
			 */
			List<Object[]> queryResult = queryRepository.getAllResultOfQuery(params.getJSONObject("queryInfo").getString("jpqlQuery"), params.getJSONArray("queryCriteria"), params
					.getJSONObject("queryInfo").getInt("queryType"));

			/*
			 * merge the result of query with fields
			 */
			data = mergeColumnsWithData(queryResult, getFieldList(params.getJSONObject("queryInfo").getString("queryJSON")));

		} catch (Exception ex) {
			throw ex;
		}
		return data;

	}

	private void changeSessionDataInQueryCriterial(JSONObject params) {
		try {
			if (params.has("queryCriteria")) {
				JSONArray queryCriteria = params.getJSONArray("queryCriteria");
				if (queryCriteria != null && queryCriteria.length() > 0) {
					for (int i = 0; i < queryCriteria.length(); i++) {
						JSONObject currentCriteria = queryCriteria.getJSONObject(i);
						if (currentCriteria.has("sessionInput") && currentCriteria.getBoolean("sessionInput") == true) {
							String sessionDataKey = currentCriteria.getString("attributeName");
							if (sessionDataAttribute != null) {
								Object sessionValue = sessionDataAttribute.getSessionData(sessionDataKey);
								if (sessionValue != null) {
									currentCriteria.put("value", sessionValue);
								}
							}
						}
					}
				}

			}
		} catch (Exception ex) {

		}

	}

	// /get select field from query details json
	private JSONArray getFieldList(String queryJSON) throws Exception {
		JSONArray fields = new JSONArray();
		try {
			JSONObject qJson = new JSONObject(queryJSON);
			fields = qJson.getJSONArray("fields");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("Error occure while getting fileds JSON from Query JSON Data of Query:" + ex.getMessage());
		}
		return fields;
	}

	/*
	 * merger result of query with columns by matching order of columns in
	 * definition of query
	 */
	private JSONArray mergeColumnsWithData(List<Object[]> result, JSONArray fielsDetails) throws Exception {
		JSONArray datas = new JSONArray();
		JSONObject data = new JSONObject();
		/*
		 * used to store datatype of data
		 */
		JSONObject datatype = new JSONObject();
		/*
		 * used to store long value if the datatype is Date,Timestamp
		 */
		JSONObject dateLongValue = new JSONObject();
		try {
			for (Object currentObject : result) {
				data = new JSONObject();
				datatype = new JSONObject();
				dateLongValue = new JSONObject();
				if (currentObject instanceof Object[]) {
					Object[] objects = (Object[]) currentObject;
					for (int x = 0; x < fielsDetails.length(); x++) {
						JSONObject field = fielsDetails.getJSONObject(x);
						datatype.put(field.getString("name"), field.getString("dataType"));
						if (objects[x] instanceof Date) {
							dateLongValue.put(field.getString("name"), ((Date) objects[x]).getTime());
						} else if (objects[x] instanceof Timestamp) {
							dateLongValue.put(field.getString("name"), ((Timestamp) objects[x]).getTime());
						}
						{
							data.put(field.getString("name"), objects[x]);
						}
					}

					datas.put(data);
				} else {
					for (int x = 0; x < fielsDetails.length(); x++) {
						JSONObject field = fielsDetails.getJSONObject(x);
						datatype.put(field.getString("name"), field.getString("dataType"));
						if (currentObject instanceof Date) {
							dateLongValue.put(field.getString("name"), ((Date) currentObject).getTime());
						} else if (currentObject instanceof Timestamp) {
							dateLongValue.put(field.getString("name"), ((Timestamp) currentObject).getTime());
						}
						{
							data.put(field.getString("name"), currentObject);
						}
					}

					datas.put(data);
				}

			}

		} catch (Exception ex) {

			ex.printStackTrace();
			throw new Exception("Error while settings data with fiels:" + ex.getMessage());
		}
		return datas;
	}
}
