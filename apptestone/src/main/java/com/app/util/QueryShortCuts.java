package com.app.util;
import org.springframework.stereotype.Component;

import atg.taglib.json.util.JSONObject;
@Component
public class QueryShortCuts {

	public final String FROM_DATE = "From Date";

	public final String TO_DATE = "To Date";

	public final String VALUE = "value";
	
	
	public long getShortCutDateLongVal(JSONObject jsonObject) throws Exception {
		
		if (!jsonObject.has("dateFlag")) {
			throw new Exception("Invalid default date data");
		}

		String dateFlag = jsonObject.getString("dateFlag");
		switch (dateFlag) {
		case "TODAY":
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(FROM_DATE)) {
				return DateShortCuts.todayStartTime();
			}
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(TO_DATE)) {
				return DateShortCuts.todayEndTime();
			}
			break;
			
		case "YESTERDAY":
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(FROM_DATE)) {
				return DateShortCuts.yesterdayStartTime();
			}
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(TO_DATE)) {
				return DateShortCuts.yesterdayEndTime();
			}
			break;
			
		case "DAY_B4_YESTERDAY":
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(FROM_DATE)) {
				return DateShortCuts.dayB4YesterdayStartTime();
			}
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(TO_DATE)) {
				return DateShortCuts.dayB4YesterdayEndTime();
			}
			break;
			
		case "THIS_WEEK":
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(FROM_DATE)) {
				return DateShortCuts.currentWeekFirstDate();
			}
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(TO_DATE)) {
				return DateShortCuts.currentWeekLastDate();
			}
			break;
			
		case "LAST_WEEK":
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(FROM_DATE)) {
				return DateShortCuts.lastWeekFirstDate();
			}
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(TO_DATE)) {
				return DateShortCuts.lastWeekEndDate();
			}
			break;
			
		case "THIS_MONTH":
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(FROM_DATE)) {
				return DateShortCuts.currentMonthFirstDate();
			}
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(TO_DATE)) {
				return DateShortCuts.currentMonthLastDate();
			}
			break;
			
		case "LAST_MONTH":
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(FROM_DATE)) {
				return DateShortCuts.lastMonthFirstDate();
			}
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(TO_DATE)) {
				return DateShortCuts.todayEndTime();
			}
			break;
			
		case "LAST_3_MONTH":
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(FROM_DATE)) {
				return DateShortCuts.last3MonthFirstDate();
			}
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(TO_DATE)) {
				return DateShortCuts.todayEndTime();
			}
			break;
			
		case "LAST_6_MONTH":
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(FROM_DATE)) {
				return DateShortCuts.last6MonthFirstDate();
			}
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(TO_DATE)) {
				return DateShortCuts.todayEndTime();
			}
			break;
			
		case "THIS_YEAR":
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(FROM_DATE)) {
				return DateShortCuts.currentYearFirstDate();
			}
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(TO_DATE)) {
				return DateShortCuts.currentYearLastDate();
			}
			break;
			
		case "LAST_YEAR":
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(FROM_DATE)) {
				return DateShortCuts.lastYearFirstDate();
			}
			if (jsonObject.has("label")&& jsonObject.getString("label").equals(TO_DATE)) {
				return DateShortCuts.lastYearLastDate();
			}
			break;

		default:
			break;
		}
		
		throw new Exception("Unhandle condition");
	}
	
	public long getCustomDateLongVal(JSONObject jsonObject, String timeZoneLabel) throws Exception {
		if (!jsonObject.has("customDateValue")) {
			throw new Exception("Invalid Custom Date data");
		}
		String userDate=jsonObject.getString("customDateValue");
		return DateShortCuts.convertToUTC(userDate, timeZoneLabel);
	}
	
	

}
