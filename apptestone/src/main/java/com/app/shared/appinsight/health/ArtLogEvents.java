package com.app.shared.appinsight.health;
import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "art_log_events_t")
@Entity
public class ArtLogEvents implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "logeventId")
	@JsonProperty("logeventId")
	@GeneratedValue(generator = "UUIDGenerator")
	private String logeventId;

	@Column(name = "eventDateTime")
	private Timestamp eventDateTime;

	@Column(name = "eventDate")
	private Date eventDate;

	@Column(name = "alarmId")
	private String alarmId;

	@Column(name = "multiTenantId", insertable = false, updatable = false)
	@JsonProperty("multiTenantId")
	private String multiTenantId;

	@Column(name = "userId")
	private String userId;

	@Column(name = "userIpAddress")
	private String userIpAddress;

	@Column(name = "userPort")
	private int userPort;

	@Column(name = "geoLatitude")
	private double geoLatitude;

	@Column(name = "geoLongitude")
	private double geoLongitude;

	@Column(name = "sessionId")
	private String sessionId;

	@Column(name = "requestId")
	private String requestId;

	@Column(name = "boundedContextId")
	private String boundedContextId;

	@Column(name = "domainId")
	private String domainId;

	@Column(name = "className")
	private String className;

	@Column(name = "methodName")
	private String methodName;

	@Column(name = "message")
	private String message;

	@Column(name = "exception")
	private String exception;

	@Column(name = "context")
	private int context;

	@Column(name = "severity")
	private int severity;

	@Column(name = "layerId")
	private int layerId;

	@Column(name = "eventAction")
	private int eventAction;

	@Column(name = "alarmStatus")
	private int alarmStatus;

	public ArtLogEvents() {
		super();
	}

	public ArtLogEvents(String logeventId, Timestamp eventDateTime, Date eventDate, String alarmId, String multiTenantId, String userId, String userIpAddress, int userPort,
			double geoLatitude, double geoLongitude, String sessionId, String requestId, String boundedContextId, String domainId, String className, String methodName,
			String message, String exception, int context, int severity, int layerId, int eventAction, int alarmStatus) {
		super();
		this.logeventId = logeventId;
		this.eventDateTime = (eventDateTime == null ? eventDateTime : new Timestamp(eventDateTime.getTime()));
		this.eventDate = (eventDate == null ? eventDate : new Date(eventDate.getTime()));
		this.alarmId = alarmId;
		this.multiTenantId = multiTenantId;
		this.userId = userId;
		this.userIpAddress = userIpAddress;
		this.userPort = userPort;
		this.geoLatitude = geoLatitude;
		this.geoLongitude = geoLongitude;
		this.sessionId = sessionId;
		this.requestId = requestId;
		this.boundedContextId = boundedContextId;
		this.domainId = domainId;
		this.className = className;
		this.methodName = methodName;
		this.message = message;
		this.exception = exception;
		this.context = context;
		this.severity = severity;
		this.layerId = layerId;
		this.eventAction = eventAction;
		this.alarmStatus = alarmStatus;
	}

	public String getLogeventId() {
		return logeventId;
	}

	public void setLogeventId(final String logeventId) {
		this.logeventId = logeventId;
	}

	public Timestamp getEventDateTime() {
		return (eventDateTime == null ? eventDateTime : new Timestamp(eventDateTime.getTime()));
	}

	public void setEventDateTime(final Timestamp eventDateTime) {
		this.eventDateTime = (eventDateTime == null ? eventDateTime : new Timestamp(eventDateTime.getTime()));

	}

	public Date getEventDate() {
		return (eventDate == null ? eventDate : new Date(eventDate.getTime()));
	}

	public void setEventDate(final Date eventDate) {
		this.eventDate = (eventDate == null ? eventDate : new Date(eventDate.getTime()));
	}

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(final String alarmId) {
		this.alarmId = alarmId;
	}

	public String getMultiTenantId() {
		return multiTenantId;
	}

	public void setMultiTenantId(final String multiTenantId) {
		this.multiTenantId = multiTenantId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getUserIpAddress() {
		return userIpAddress;
	}

	public void setUserIpAddress(final String userIpAddress) {
		this.userIpAddress = userIpAddress;
	}

	public int getUserPort() {
		return userPort;
	}

	public void setUserPort(final int userPort) {
		this.userPort = userPort;
	}

	public double getGeoLatitude() {
		return geoLatitude;
	}

	public void setGeoLatitude(final double geoLatitude) {
		this.geoLatitude = geoLatitude;
	}

	public double getGeoLongitude() {
		return geoLongitude;
	}

	public void setGeoLongitude(final double geoLongitude) {
		this.geoLongitude = geoLongitude;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(final String requestId) {
		this.requestId = requestId;
	}

	public String getBoundedContextId() {
		return boundedContextId;
	}

	public void setBoundedContextId(final String boundedContextId) {
		this.boundedContextId = boundedContextId;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(final String domainId) {
		this.domainId = domainId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(final String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(final String methodName) {
		this.methodName = methodName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(final String exception) {
		this.exception = exception;
	}

	public int getContext() {
		return context;
	}

	public void setContext(final int context) {
		this.context = context;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(final int severity) {
		this.severity = severity;
	}

	public int getLayerId() {
		return layerId;
	}

	public void setLayerId(final int layerId) {
		this.layerId = layerId;
	}

	public int getEventAction() {
		return eventAction;
	}

	public void setEventAction(final int eventAction) {
		this.eventAction = eventAction;
	}

	public int getAlarmStatus() {
		return alarmStatus;
	}

	public void setAlarmStatus(final int alarmStatus) {
		this.alarmStatus = alarmStatus;
	}

	@Override
	public String toString() {
		return "ArtLogAlarmEvents [logeventId=" + logeventId + ", eventDateTime=" + eventDateTime + ", eventDate=" + eventDate + ", alarmId=" + alarmId + ", multiTenantId="
				+ multiTenantId + ", userId=" + userId + ", userIpAddress=" + userIpAddress + ", userPort=" + userPort + ", geoLatitude=" + geoLatitude + ", geoLongitude="
				+ geoLongitude + ", sessionId=" + sessionId + ", requestId=" + requestId + ", boundedContextId=" + boundedContextId + ", domainId=" + domainId + ", className="
				+ className + ", methodName=" + methodName + ", message=" + message + ", exception=" + exception + ", context=" + context + ", severity=" + severity + ", layerId="
				+ layerId + ", eventAction=" + eventAction + ", alarmStatus=" + alarmStatus + "]";
	}

	public String toJSON() {
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("logeventId", "logeventId");
			jsonObj.put("eventDateTime", "eventDateTime");
			jsonObj.put("eventDate", "eventDate");
			jsonObj.put("alarmId", alarmId);
			jsonObj.put("multiTenantId", multiTenantId);
			jsonObj.put("severity", severity);
			jsonObj.put("userId", "userId");
			jsonObj.put("userIpAddress", "userIpAddress");
			jsonObj.put("userPort", "userPort");
			jsonObj.put("geoLatitude", geoLatitude);
			jsonObj.put("geoLongitude", geoLongitude);
			jsonObj.put("sessionId", "sessionId");
			jsonObj.put("requestId", "requestId");
			jsonObj.put("boundedContextId", "boundedContextId");
			jsonObj.put("domainId", "domainId");
			jsonObj.put("className", "className");
			jsonObj.put("methodName", "methodName");
			jsonObj.put("message", message);
			jsonObj.put("exception", exception);
			jsonObj.put("context", context);
			jsonObj.put("layerId", layerId);
			jsonObj.put("eventActions", eventAction);
			jsonObj.put("alarmStatus", alarmStatus);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObj.toString();
	}
}
