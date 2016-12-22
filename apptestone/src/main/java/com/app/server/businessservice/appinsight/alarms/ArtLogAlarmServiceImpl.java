package com.app.server.businessservice.appinsight.alarms;
import com.app.shared.appinsight.alarms.ArtLogAlarm;

import com.app.shared.appinsight.alarms.ArtLogArchitectureLayer;

import com.app.shared.appinsight.alarms.ArtBoundedContext;

import com.app.shared.appinsight.alarms.ArtLogConfig;

import com.app.shared.appinsight.alarms.ArtLogConnector;

import com.app.shared.appinsight.alarms.ArtDomain;

import com.app.shared.appinsight.alarms.ArtLogEventAction;

import com.app.shared.appinsight.alarms.ArtLogSeverity;

import com.app.shared.appinsight.alarms.ArtLogStatus;

import com.app.server.repository.appinsight.alarms.ArtAlarmLoggerRepository;

import com.app.server.repository.appinsight.alarms.ArtBoundedContextRepository;

import com.app.server.repository.appinsight.alarms.ArtDomainRepository;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import com.athena.server.pluggable.utils.bean.ResponseBean;

@RestController
@RequestMapping("/logAlarmService")
public class ArtLogAlarmServiceImpl extends ArtLogAlarmService {

	@Autowired
	ArtAlarmLoggerRepository artLoggerRepository;

	@Autowired
	private ArtBoundedContextRepository artBoundedContextRepository;

	@Autowired
	private ArtDomainRepository artDomainRepository;

	@Override
	@RequestMapping(value = "/getListOfAlarms", consumes = "application/json", method = RequestMethod.GET)
	public HttpEntity<ResponseBean> getListOfAlarms() {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		JSONArray contextArray = new JSONArray();
		responseBean.add("success", true);
		responseBean.add("message", "Successfully retrived ");
		responseBean.add("data", contextArray.toString());
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

	@Override
	@RequestMapping(value = "/getLoggerDetails", consumes = "application/json", method = RequestMethod.GET)
	public HttpEntity<ResponseBean> getLogConfigDetails() {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		try {
			List<ArtLogConfig> artLogList = artLoggerRepository.findAll();
			if (artLogList.size() > 0) {
				StringBuilder artLoggerDetails = new StringBuilder();
				responseBean.add("success", true);
				responseBean.add("message", "Successfully retrived ");
				responseBean.add("data", artLoggerDetails.toString());
				return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
			} else {
				responseBean.add("success", false);
				responseBean.add("message", "Data cannot be retrived ");
				return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private StringBuilder getSeverityJson(List<ArtLogSeverity> artLogSeverity, StringBuilder artLoggerDetails) {
		artLoggerDetails.append("\"alarmSeverity\":[ ");
		if (artLogSeverity != null && !artLogSeverity.isEmpty()) {
			for (ArtLogSeverity logSeverity : artLogSeverity) {
				artLoggerDetails.append(logSeverity.toSeverityDetails() + ",");
			}
			artLoggerDetails.deleteCharAt(artLoggerDetails.length() - 1);
		}
		artLoggerDetails.append("]");
		return artLoggerDetails;
	}

	private StringBuilder getConnectorArrayJson(List<ArtLogConnector> artLogConnectors, StringBuilder artLoggerDetails) {
		artLoggerDetails.append("\"connectorArray\":[");
		if (artLogConnectors != null && !artLogConnectors.isEmpty()) {
			for (ArtLogConnector connector : artLogConnectors) {
				artLoggerDetails.append(connector.toConnectorDetails() + ",");
			}
			artLoggerDetails.deleteCharAt(artLoggerDetails.length() - 1);
		}
		artLoggerDetails.append("]");
		return artLoggerDetails;
	}

	/*private StringBuilder getExceptionJson(List<ArtLogException> artLogExceptions, StringBuilder artLoggerDetails) {
		artLoggerDetails.append("\"exceptions\":[ ");
		if (artLogExceptions.size() > 0 && artLogExceptions != null && !artLogExceptions.isEmpty()) {
			for (ArtLogException awsLogException : artLogExceptions) {
				artLoggerDetails.append(awsLogException.toJSON() + ",");
			}
			artLoggerDetails.deleteCharAt(artLoggerDetails.length() - 1);
		}
		artLoggerDetails.append("]");
		return artLoggerDetails;
	}*/

	private StringBuilder getStatusJson(List<ArtLogStatus> artLogStatusList, StringBuilder artLoggerDetails) {
		artLoggerDetails.append("\"alarmStatus\":[ ");
		if (artLogStatusList.size() > 0 && artLogStatusList != null && !artLogStatusList.isEmpty()) {
			for (ArtLogStatus logStatus : artLogStatusList) {
				artLoggerDetails.append(logStatus.toJSON() + ",");
			}
			artLoggerDetails.deleteCharAt(artLoggerDetails.length() - 1);
		}
		artLoggerDetails.append("]");
		return artLoggerDetails;
	}

	private StringBuilder getEventActionJson(List<ArtLogEventAction> artLogEventActions, StringBuilder artLoggerDetails) {
		artLoggerDetails.append("\"eventActions\":[ ");
		if (artLogEventActions.size() > 0 && artLogEventActions != null && !artLogEventActions.isEmpty()) {
			for (ArtLogEventAction eventAction : artLogEventActions) {
				artLoggerDetails.append(eventAction.toJSON() + ",");
			}
			artLoggerDetails.deleteCharAt(artLoggerDetails.length() - 1);
		}
		artLoggerDetails.append("]");
		return artLoggerDetails;
	}

	private StringBuilder getArchitectureLayers(List<ArtLogArchitectureLayer> artLogArchitectureLayers, StringBuilder artLoggerDetails) {
		artLoggerDetails.append("\"architectureLayers\":[ ");
		if (artLogArchitectureLayers.size() > 0 && artLogArchitectureLayers != null && !artLogArchitectureLayers.isEmpty()) {
			for (ArtLogArchitectureLayer appArchitectureLayer : artLogArchitectureLayers) {
				artLoggerDetails.append(appArchitectureLayer.toJSON() + ",");
			}
			artLoggerDetails.deleteCharAt(artLoggerDetails.length() - 1);
		}
		artLoggerDetails.append("]");
		return artLoggerDetails;
	}

	@Override
	@RequestMapping(value = "/updateLogAlarm", consumes = "application/json", method = RequestMethod.PUT)
	public HttpEntity<ResponseBean> updateLogAlarm() {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		responseBean.add("success", true);
		responseBean.add("message", "Alarms updated successfully");
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

	@Override
	@RequestMapping(value = "/getGridData", consumes = "application/json", method = RequestMethod.GET)
	public HttpEntity<ResponseBean> getGridData(@RequestBody String domainId) throws SecurityException {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		responseBean.add("success", true);
		responseBean.add("message", "Error in grid data retrieval");
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}
}
