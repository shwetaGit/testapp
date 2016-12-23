package com.app.shared.appinsight.alarms;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

@Entity
@Table(name = "art_log_connector_m")
@Cache(alwaysRefresh = true)
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "connectorId")
public class ArtLogConnector implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	@Basic(optional = false)
	@Column(name = "connectorId")
	private String connectorId;

	@ManyToOne
	@JoinColumn(name = "logConfigId", referencedColumnName = "logConfigId")
	private ArtLogConfig artLogConfig;

	@Column(name = "connectorLogLevel")
	private Integer logLevel;

	@Column(name = "cid")
	private Integer cid;

	@Column(name = "id")
	private String id;

	@Column(name = "enabled")
	private boolean isEnabled;

	@Column(name = "connectorName")
	private String connectorName;

	@Column(name = "className")
	private String className;

	@Column(name = "systemDefined")
	private boolean systemDefined;

	@OneToMany(mappedBy = "artLogConnector", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ArtLogConnectorAttributes> logConnectorAttributes;

	public ArtLogConnector() {
		super();
	}

	public ArtLogConnector(String id, Integer cid, ArtLogConfig artLogConfig, Integer logLevel, boolean isEnabled, String connectorName, String className, boolean systemDefined) {
		this.id = id;
		this.cid = cid;
		this.artLogConfig = artLogConfig;
		this.logLevel = logLevel;
		this.isEnabled = isEnabled;
		this.connectorName = connectorName;
		this.className = className;
		this.systemDefined = systemDefined;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getConnectorId() {
		return connectorId;
	}

	public void setConnectorId(final String connectorId) {
		this.connectorId = connectorId;
	}

	public ArtLogConfig getArtLogConfig() {
		return artLogConfig;
	}

	public void setArtLogConfig(final ArtLogConfig artLogConfig) {
		this.artLogConfig = artLogConfig;
	}

	public Integer getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(final Integer logLevel) {
		this.logLevel = logLevel;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(final boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getConnectorName() {
		return connectorName;
	}

	public void setConnectorName(final String connectorName) {
		this.connectorName = connectorName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(final String className) {
		this.className = className;
	}

	public List<ArtLogConnectorAttributes> getLogConnectorAttributes() {
		return logConnectorAttributes;
	}

	public void setLogConnectorAttributes(final List<ArtLogConnectorAttributes> logConnectorAttributes) {
		this.logConnectorAttributes = logConnectorAttributes;
	}

	public boolean isSystemDefined() {
		return systemDefined;
	}

	public void setSystemDefined(final boolean systemDefined) {
		this.systemDefined = systemDefined;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(final Integer cid) {
		this.cid = cid;
	}

	@Override
	public String toString() {
		return "ArtLogConnector [connectorId=" + connectorId + ", logSeverity=" + logLevel + ", isEnabled=" + isEnabled + ", connectorName=" + connectorName + ", className="
				+ className + ", systemDefined=" + systemDefined + ", logConnectorAttributes=" + logConnectorAttributes + "]";
	}

	public String toJSON() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"cid\":" + "\"" + getCid() + "\"");
		sb.append(",\"attributeId\":" + "\"" + getConnectorId() + "\"");
		sb.append(",\"enabled\":" + "\"" + isEnabled() + "\"");
		sb.append(",\"logLevel\":" + "\"" + logLevel + "\"");
		sb.append(",\"name\":" + "\"" + getConnectorName() + "\"");
		sb.append(",\"className\":" + "\"" + getClassName() + "\"");
		sb.append(",\"systemDefined\":" + "\"" + isSystemDefined() + "\"");

		sb.append(",\"logConnectorProperties\":[");
		if (logConnectorAttributes != null && !logConnectorAttributes.isEmpty()) {
			for (ArtLogConnectorAttributes loggingPropertie : logConnectorAttributes) {
				sb.append(loggingPropertie.toJSON() + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]").append("}");
		return sb.toString();

	}

	public String toConnectorDetails() {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append("\"cid\":" + "\"" + getCid() + "\"").append(",\"name\":" + "\"" + getConnectorName() + "\"").append("}");
		return sb.toString();
	}

	public String toXMLString() {
		StringBuilder buf = new StringBuilder();
		buf.append("<connector").append(" cid=\"").append(this.cid).append("\" logLevel=\"").append(this.logLevel).append("\">\n");
		buf.append("<name enable=\"").append(this.isEnabled).append("\" class=\"").append(this.className).append("\" ").append(" id=\"").append(this.id)
				.append("\" isSystemDefined=\"" + this.systemDefined + "").append("\">").append(connectorName).append("</name>\n");
		buf.append("<map>\n");
		for (ArtLogConnectorAttributes logProperty : logConnectorAttributes) {
			if (!logProperty.getAttributeComment().equalsIgnoreCase("")) {
				buf.append("<!-- " + logProperty.getAttributeComment() + " -->\n");
			}
			buf.append("<attribute name=\"").append(logProperty.getAttributeName()).append("\">");

			if (logProperty.getAttributeValue().matches("[a-zA-Z0-9_./ ]*")) {
				buf.append(logProperty.getAttributeValue());
			} else {
				buf.append("<![CDATA[" + logProperty.getAttributeValue() + "]]>");
			}
			buf.append("</attribute>\n");
		}
		buf.append("</map>\n");
		buf.append("</connector>\n");
		return buf.toString();
	}

}
