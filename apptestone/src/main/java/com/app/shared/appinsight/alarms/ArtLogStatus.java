package com.app.shared.appinsight.alarms;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;

@Entity
@Table(name = "art_log_status_m")
@Cache(alwaysRefresh = true)
public class ArtLogStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "statusId")
	private Integer statusId;

	@Column(name = "alarmStatus")
	private String alarmStatus;

	public ArtLogStatus() {
		super();
	}

	public ArtLogStatus(Integer statusId, String alarmStatus) {
		super();
		this.statusId = statusId;
		this.alarmStatus = alarmStatus;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(final Integer statusId) {
		this.statusId = statusId;
	}

	public String getAlarmStatus() {
		return alarmStatus;
	}

	public void setAlarmStatus(final String alarmStatus) {
		this.alarmStatus = alarmStatus;
	}

	@Override
	public String toString() {
		return "ArtLogStatus [statusId=" + statusId + ", alarmStatus=" + alarmStatus + "]";
	}

	public String toJSON() {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append("\"statusId\":" + "\"" + getStatusId() + "\"").append(",\"alarmStatus\":" + "\"" + getAlarmStatus() + "\"").append("}");
		return sb.toString();
	}
}
