package com.app.shared.appinsight.alarms;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

import com.athena.shared.pluggable.entity.AuditPropertiesEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "art_log_alarm")
@Entity
@Cache(type = CacheType.CACHE)
public class ArtLogAlarm extends AuditPropertiesEntity {

	@Transient
	private String primaryKey;

	@Id
	@Column(name = "loggerPkId")
	@JsonProperty("loggerPkId")
	@GeneratedValue(generator = "UUIDGenerator")
	private String loggerPkId;

	@Column(name = "alarmType")
	@JsonProperty("alarmType")
	@NotNull
	private int alarmType;

	@Column(name = "alarmData")
	@JsonProperty("alarmData")
	@NotNull
	private String alarmData;

	@Column(name = "alarmVersion")
	@JsonProperty("alarmVersion")
	@NotNull
	private int alarmVersion;

	public ArtLogAlarm() {
		super();
	}

	public int getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(final int alarmType) {
		this.alarmType = alarmType;
	}

	public String getAlarmData() throws Exception {
		return this.decodeJSON(alarmData);
	}

	public void setAlarmData(final String alarmData) throws Exception {
		this.alarmData = this.encodeJSON(alarmData);
	}

	public int getAlarmVersion() {
		return alarmVersion;
	}

	public void setAlarmVersion(final int alarmVersion) {
		this.alarmVersion = alarmVersion;
	}

}
