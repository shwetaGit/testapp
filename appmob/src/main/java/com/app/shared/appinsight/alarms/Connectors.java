package com.app.shared.appinsight.alarms;
import java.util.List;

public class Connectors {
	private String id;

	private boolean enabled;

	private List<LogConnectorProperties> logConnectorProperties;

	private Integer logLevel;

	private String name;

	private String className;

	private Integer cid;
	private String attributeId;

	private boolean systemDefined;

	public boolean isSystemDefined() {
		return systemDefined;
	}

	public void setSystemDefined(final boolean systemDefined) {
		this.systemDefined = systemDefined;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(final String attributeId) {
		this.attributeId = attributeId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public List<LogConnectorProperties> getLogConnectorProperties() {
		return logConnectorProperties;
	}

	public void setLogConnectorProperties(final List<LogConnectorProperties> logConnectorProperties) {
		this.logConnectorProperties = logConnectorProperties;
	}

	public Integer getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(final Integer logLevel) {
		this.logLevel = logLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(final String className) {
		this.className = className;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(final Integer cid) {
		this.cid = cid;
	}

	@Override
	public String toString() {
		return "Connectors [id=" + id + ", enabled=" + enabled + ", logConnectorProperties=" + logConnectorProperties + ", logLevel=" + logLevel + ", name=" + name
				+ ", className=" + className + ", cid=" + cid + ", systemDefined=" + systemDefined + "]";
	}
}
