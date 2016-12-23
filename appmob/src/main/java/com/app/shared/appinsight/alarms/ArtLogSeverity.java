package com.app.shared.appinsight.alarms;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;

@Entity
@Table(name = "art_log_severity_m")
@Cache(alwaysRefresh = true)
public class ArtLogSeverity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "severity")
	private String severity;

	@Id
	@Column(name = "severityId")
	private Integer severityId;

	public ArtLogSeverity() {
		super();
	}

	public ArtLogSeverity(Integer severityId, String severity) {
		this.severityId = severityId;
		this.severity = severity;
	}

	public Integer getSeverityId() {
		return severityId;
	}

	public void setSeverityId(final Integer severityId) {
		this.severityId = severityId;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(final String severity) {
		this.severity = severity;
	}

	@Override
	public String toString() {
		return "ArtLogSeverity [severityId=" + severityId + ", severity=" + severity + "]";
	}

	public String toJSON() {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append("\"attributeName\":" + "\"" + getSeverityId() + "\"").append(",\"attributeValue\":" + "\"" + getSeverity() + "\"").append("}");
		return sb.toString();
	}

	public String toSeverityDetails() {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append("\"severityId\":" + "\"" + getSeverityId() + "\"").append(",\"severity\":" + "\"" + getSeverity() + "\"").append("}");
		return sb.toString();

	}

}
